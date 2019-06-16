package com.neirx.testalarmclock.di.module;

import com.neirx.testalarmclock.ui.create_alarm.EditAlarmActivity;
import com.neirx.testalarmclock.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Module
public abstract class AppComponentModule {

    @ContributesAndroidInjector(modules = {ViewModule.class, PresenterModule.class})
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {ViewModule.class, PresenterModule.class})
    abstract EditAlarmActivity contributeEditAlarmActivity();
}
