package com.neirx.testalarmclock.di.module;

import com.neirx.testalarmclock.contract.ui.EditAlarmScreen;
import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.ui.create_alarm.EditAlarmActivity;
import com.neirx.testalarmclock.ui.main.MainActivity;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Module
public abstract class ViewModule {

    @Binds
    public abstract MainScreen.View bindMainView(MainActivity activity);

    @Binds
    public abstract EditAlarmScreen.View bindEditAlarmView(EditAlarmActivity alarmActivity);
}
