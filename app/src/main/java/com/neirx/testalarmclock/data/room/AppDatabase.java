package com.neirx.testalarmclock.data.room;

import com.neirx.testalarmclock.data.room.dao.AlarmClockDAO;
import com.neirx.testalarmclock.data.room.entity.AlarmClockEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Database(entities = {AlarmClockEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "test_alarm_clock_db";
    public static final String TABLE_ALARM_CLOCKS = "alarm_clocks";

    public abstract AlarmClockDAO alarmClockDAO() ;
}
