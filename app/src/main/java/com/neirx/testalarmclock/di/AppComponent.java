package com.neirx.testalarmclock.di;

import android.app.Application;

import com.neirx.testalarmclock.app.ThisApp;
import com.neirx.testalarmclock.di.module.AppComponentModule;
import com.neirx.testalarmclock.di.module.RoomModule;
import com.neirx.testalarmclock.di.module.ViewModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Component(modules = {
        AppComponentModule.class,
        AndroidInjectionModule.class,
        ViewModule.class,
        RoomModule.class})
@Singleton
public interface AppComponent {


    /* We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     * */
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }


    /*
     * This is our custom Application class
     * */
    void inject(ThisApp appController);
}

