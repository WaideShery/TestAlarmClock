package com.neirx.testalarmclock.data.room.dao;

import com.neirx.testalarmclock.data.room.entity.AlarmClockEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Dao
public abstract class AlarmClockDAO {
    @Query("SELECT * FROM alarm_clocks")
    public abstract List<AlarmClockEntity> getAlarmClocks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addAlarmClock(AlarmClockEntity entity);

    @Query("DELETE FROM alarm_clocks WHERE id LIKE :id")
    public abstract void deleteAlarmClockById(long id);
}
