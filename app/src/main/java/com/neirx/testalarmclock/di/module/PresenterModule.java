package com.neirx.testalarmclock.di.module;

import com.neirx.testalarmclock.contract.ui.EditAlarmScreen;
import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.data.repository.AlarmClockRepository;
import com.neirx.testalarmclock.ui.create_alarm.EditAlarmPresenter;
import com.neirx.testalarmclock.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Waide Shery on 16.06.19.
 */
@Module
public class PresenterModule {

    @Provides
    public MainScreen.Presenter bindMainPresenter(MainScreen.View view,
                                                        AlarmClockRepository alarmClockRepository) {
        return new MainPresenter(view, alarmClockRepository);
    }

    @Provides
    public EditAlarmScreen.Presenter bindEditAlarmPresenter(EditAlarmScreen.View view,
                                                             AlarmClockRepository alarmClockRepository) {
        return new EditAlarmPresenter(view, alarmClockRepository);
    }
}
