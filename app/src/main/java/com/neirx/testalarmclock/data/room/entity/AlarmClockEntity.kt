package com.neirx.testalarmclock.data.room.entity;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neirx.testalarmclock.data.room.AppDatabase

/**
 * Created by Waide Shery on 03.06.19.
 */
@Entity(tableName = AppDatabase.TABLE_ALARM_CLOCKS)
data class AlarmClockEntity(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @ColumnInfo(name = "enabled")
        var enabled: Int = 0

)
