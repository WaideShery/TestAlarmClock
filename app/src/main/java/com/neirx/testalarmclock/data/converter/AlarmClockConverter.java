package com.neirx.testalarmclock.data.converter;

import com.neirx.testalarmclock.data.room.entity.AlarmClockEntity;
import com.neirx.testalarmclock.model.AlarmClock;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class AlarmClockConverter {

    public static AlarmClock convert(AlarmClockEntity entity){
        AlarmClock alarmClock = new AlarmClock();
        alarmClock.setId(entity.getId());
        return alarmClock;
    }

    public static AlarmClockEntity convert(AlarmClock alarmClock){
        AlarmClockEntity entity = new AlarmClockEntity();
        entity.setId(alarmClock.getId());
        return entity;
    }
}
