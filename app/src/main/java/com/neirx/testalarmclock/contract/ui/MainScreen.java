package com.neirx.testalarmclock.contract.ui;

import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.model.AlarmClock;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

/**
 * Created by Waide Shery on 03.06.19.
 */
public interface MainScreen {
    interface View extends LifecycleOwner {
        void onAlarmClocksLoaded(List<AlarmClock>alarmClocks);
        void onAlarmClocksLoadedError(AppError appError);
    }

    interface Presenter  extends BasePresenter{
        void loadAlarmClocks();
    }
}
