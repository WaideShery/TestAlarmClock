package com.neirx.testalarmclock.di.module;

import android.app.Application;

import com.neirx.testalarmclock.data.room.AppDatabase;
import com.neirx.testalarmclock.data.room.dao.AlarmClockDAO;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Module
public abstract class RoomModule {
    private AppDatabase appDatabase;

    @Provides
    @Singleton
    static AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    static AlarmClockDAO providesAlarmClockDAO(AppDatabase appDatabase) {
        return appDatabase.alarmClockDAO();
    }

}
