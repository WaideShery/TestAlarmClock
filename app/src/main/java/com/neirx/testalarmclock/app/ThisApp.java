package com.neirx.testalarmclock.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
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
    }

    //-------- Dagger injections BEGIN--------
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

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
