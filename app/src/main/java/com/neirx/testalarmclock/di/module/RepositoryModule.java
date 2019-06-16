package com.neirx.testalarmclock.di.module;

import com.neirx.testalarmclock.data.repository.AlarmClockRepository;
import com.neirx.testalarmclock.data.room.dao.AlarmClockDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Waide Shery on 16.06.19.
 */
@Module
public class RepositoryModule {
    @Provides
    @Singleton
    AlarmClockRepository provideAlarmClockRepository(AlarmClockDAO alarmClockDAO) {
        return new AlarmClockRepository(alarmClockDAO);
    }
}
