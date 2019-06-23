package com.neirx.testalarmclock.data.room.entity;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.neirx.testalarmclock.data.room.AppDatabase
import com.neirx.testalarmclock.data.room.type_converters.IntArrayConverter

/**
 * Created by Waide Shery on 03.06.19.
 */
@TypeConverters(IntArrayConverter::class)
@Entity(tableName = AppDatabase.TABLE_ALARM_CLOCKS)
data class AlarmClockEntity(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @ColumnInfo(name = "enabled")
        var enabled: Int = 0,

        //in minutes, from 0 to 1439
        @ColumnInfo(name = "time")
        var time: Int = 0,

        //monday ... sunday
        @ColumnInfo(name = "week_days")
        var weekDays: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0)


)
