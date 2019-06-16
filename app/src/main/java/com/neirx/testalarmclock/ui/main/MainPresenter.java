package com.neirx.testalarmclock.ui.main;

import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.app.error.AppErrorFactory;
import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.data.repository.AlarmClockRepository;
import com.neirx.testalarmclock.data.repository.base.Resource;
import com.neirx.testalarmclock.model.AlarmClock;
import com.neirx.testalarmclock.ui.app_base.presenter.Presenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Waide Shery on 03.06.19.
 */
public class MainPresenter extends Presenter<MainScreen.View>
        implements MainScreen.Presenter {

    private AlarmClockRepository alarmClockRepository;

    public MainPresenter(MainScreen.View screen, AlarmClockRepository alarmClockRepository) {
        super(screen, screen);
    }

    @Override
    public void loadAlarmClocks() {
        Disposable disposable = alarmClockRepository.loadAlarmClocks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoaded,
                        t -> onLoadedError(AppErrorFactory.createFromThrowable(t)));
        addDisposable(disposable);
    }

    private void handleLoaded(Resource<List<AlarmClock>> listResource) {
        switch (listResource.status) {
            case SUCCESS:
                onLoadedSuccessful(listResource.data);
                break;
            case ERROR:
                onLoadedError(listResource.error);
                break;
            case LOADING:
                break;
        }
    }

    private void onLoadedSuccessful(List<AlarmClock> alarmClocks) {
        if (screenReady()){
            screen.onAlarmClocksLoaded(alarmClocks);
        }
    }

    private void onLoadedError(AppError appError) {
        if (screenReady()){
            screen.onAlarmClocksLoadedError(appError);
        }
    }
}

