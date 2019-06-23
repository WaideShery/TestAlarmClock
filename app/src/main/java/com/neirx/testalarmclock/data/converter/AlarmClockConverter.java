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
        alarmClock.setTimeInMinute(entity.getTime());
        for (int i=0; i<entity.getWeekDays().length; i++){
            int dayVal = entity.getWeekDays()[i];
            alarmClock.weekDays()[i] = dayVal == 1;
        }
        return alarmClock;
    }

    public static AlarmClockEntity convert(AlarmClock alarmClock){
        AlarmClockEntity entity = new AlarmClockEntity();
        entity.setId(alarmClock.getId());
        entity.setEnabled(alarmClock.isEnabled() ? 1 : 0);
        entity.setTime(alarmClock.getTimeInMinute());
        boolean[] week = alarmClock.weekDays();
        int[] intWeek = new int[week.length];
        for (int i=0; i<week.length; i++){
            intWeek[i] = week[i] ? 1 : 0;
        }
        entity.setWeekDays(intWeek);
        return entity;
    }
}
