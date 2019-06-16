package com.neirx.testalarmclock.di.module;

import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.data.repository.AlarmClockRepository;
import com.neirx.testalarmclock.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Waide Shery on 16.06.19.
 */
@Module
public abstract class PresenterModule {

    @Provides
    public MainScreen.Presenter bindMainScreenPresenter(MainScreen.View view,
                                                        AlarmClockRepository alarmClockRepository) {
        return new MainPresenter(view, alarmClockRepository);
    }
}
