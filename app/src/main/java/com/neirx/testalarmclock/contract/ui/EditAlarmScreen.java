package com.neirx.testalarmclock.contract.ui;

import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.model.AlarmClock;

import androidx.lifecycle.LifecycleOwner;

/**
 * Created by Waide Shery on 16.06.19.
 */
public interface EditAlarmScreen {

    interface View extends LifecycleOwner {
        void onAlarmLoaded(AlarmClock alarmClock);
        void onAlarmLoadedError(AppError appError);
        void onAlarmSavedSuccessful();
        void onAlarmSavedError(AppError appError);
    }

    interface Presenter extends BasePresenter{
        void loadAlarm(long id);
        void saveAlarm(AlarmClock newAlarmClock);
    }
}
