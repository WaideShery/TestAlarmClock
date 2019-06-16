package com.neirx.testalarmclock.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.neirx.testalarmclock.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;

/**
 * Created by Waide Shery on 03.06.19.
 */
public class ThisApp extends Application  implements HasActivityInjector, HasServiceInjector{

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    //-------- Dagger injections BEGIN--------
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    //-------- Dagger injections END--------
}
