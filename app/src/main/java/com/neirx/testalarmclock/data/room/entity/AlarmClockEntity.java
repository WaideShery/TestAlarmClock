package com.neirx.testalarmclock.data.room.entity;

import com.neirx.testalarmclock.data.room.AppDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Waide Shery on 03.06.19.
 */
@Entity(tableName = AppDatabase.TABLE_ALARM_CLOCKS)
public class AlarmClockEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
