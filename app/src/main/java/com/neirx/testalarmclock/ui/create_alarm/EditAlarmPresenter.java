package com.neirx.testalarmclock.ui.create_alarm;

import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.app.error.AppErrorFactory;
import com.neirx.testalarmclock.contract.ui.EditAlarmScreen;
import com.neirx.testalarmclock.data.repository.AlarmClockRepository;
import com.neirx.testalarmclock.data.repository.base.Resource;
import com.neirx.testalarmclock.model.AlarmClock;
import com.neirx.testalarmclock.ui.app_base.presenter.Presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class EditAlarmPresenter extends Presenter<EditAlarmScreen.View>
        implements EditAlarmScreen.Presenter {

    private AlarmClockRepository alarmClockRepository;

    public EditAlarmPresenter(EditAlarmScreen.View screen, AlarmClockRepository alarmClockRepository) {
        super(screen, screen);
        this.alarmClockRepository = alarmClockRepository;
    }

    @Override
    public void loadAlarm(long id) {
        Disposable disposable = alarmClockRepository.getAlarmClockById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAlarmLoadedResult,
                        t -> onAlarmLoadedError(AppErrorFactory.createFromThrowable(t)));
        addDisposable(disposable);
    }

    private void handleAlarmLoadedResult(Resource<AlarmClock> resource) {
        switch (resource.status) {
            case SUCCESS:
                onAlarmLoaded(resource.data);
                break;
            case ERROR:
                onAlarmLoadedError(resource.error);
                break;
        }
    }

    private void onAlarmLoaded(AlarmClock alarmClock) {
        if (screenReady()){
            screen.onAlarmLoaded(alarmClock);
        }
    }

    private void onAlarmLoadedError(AppError appError) {
        if (screenReady()){
            screen.onAlarmLoadedError(appError);
        }
    }

    @Override
    public void saveAlarm(AlarmClock newAlarmClock) {
        Disposable disposable = alarmClockRepository.addAlarmClock(newAlarmClock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAlarmSavedResult,
                        t -> onAlarmSavedError(AppErrorFactory.createFromThrowable(t)));
        addDisposable(disposable);
    }

    private void handleAlarmSavedResult(Resource<AlarmClock> res) {
        switch (res.status) {
            case SUCCESS:
                onAlarmSaved(res.data);
                break;
            case ERROR:
                onAlarmSavedError(res.error);
                break;
        }
    }

    private void onAlarmSaved(AlarmClock alarmClock) {
        if (screenReady()){
            screen.onAlarmSavedSuccessful();
        }
    }

    private void onAlarmSavedError(AppError appError) {
        if (screenReady()){
            screen.onAlarmSavedError(appError);
        }
    }
}
