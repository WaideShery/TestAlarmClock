package com.neirx.testalarmclock.data.repository.base;

import com.neirx.testalarmclock.app.error.AppErrorFactory;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private Observable<Resource<ResultType>> result;

    @MainThread
    protected NetworkBoundResource() {
        Observable<Resource<ResultType>> source;
        if (shouldFetch()) {
            source = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(apiResponse -> saveCallResult(processResponse(apiResponse)))
                    .flatMap(apiResponse -> {
                        Timber.tag("TestTag").d("flatMap()");
                        return loadFromDb().toObservable().map(Resource::success);
                    })
                    .doOnError(t -> onFetchFailed())
                    .onErrorResumeNext(t -> {
                        Timber.tag("TestTag").d("onErrorResumeNext()");
                        return loadFromDb()
                                .toObservable()
                                .map(data -> Resource.error(AppErrorFactory.createFromThrowable(t), data));

                    })
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            Timber.tag("TestTag").d("not shouldFetch");
            source = loadFromDb()
                    .toObservable()
                    .map(Resource::success);
        }

        result = Observable.concat(
                loadFromDb()
                        .toObservable()
                        .map(Resource::loading)
                        .take(1),
                source
        );
    }

    public Observable<Resource<ResultType>> getAsObservable() {return result;}

    /**
     * Обработка
     */
    protected void onFetchFailed() {}

    /**
     * Извлечение ресурса из ответа сервера.
     */
    @WorkerThread
    protected RequestType processResponse(Resource<RequestType> response) {return response.data;}

    /**
     * Сохранение результатов с сервера в локальную базу.
     */
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    /**
     * Проверка загрузки из/на сервер.
     * @return true - можно использовать запрос на сервер
     */
    @MainThread
    protected abstract boolean shouldFetch();


    /**
     * Загрузка данных из локально БД.
     */
    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    /**
     * Создание запроса на сервер.
     */
    @NonNull
    @MainThread
    protected abstract Observable<Resource<RequestType>> createCall();
}