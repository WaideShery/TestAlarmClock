package com.neirx.testalarmclock.data.repository;

import com.neirx.testalarmclock.data.converter.AlarmClockConverter;
import com.neirx.testalarmclock.data.repository.base.Resource;
import com.neirx.testalarmclock.data.room.dao.AlarmClockDAO;
import com.neirx.testalarmclock.data.room.entity.AlarmClockEntity;
import com.neirx.testalarmclock.model.AlarmClock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class AlarmClockRepository {
    private AlarmClockDAO alarmClockDAO;

    public AlarmClockRepository(AlarmClockDAO alarmClockDAO) {
        this.alarmClockDAO = alarmClockDAO;
    }

    public Observable<Resource<List<AlarmClock>>> subscribeAlarmClocks() {
        return alarmClockDAO.subscribeAlarmClocks().map(entities -> {
            List<AlarmClock> list = new ArrayList<>();
            for (AlarmClockEntity e : entities) list.add(AlarmClockConverter.convert(e));
            return Resource.success(list);
        }).toObservable();
    }

    public Observable<Resource<List<AlarmClock>>> loadAlarmClocks() {
        return Single.fromCallable(() -> alarmClockDAO.getAlarmClocks()).map(entities -> {
            List<AlarmClock> list = new ArrayList<>();
            for (AlarmClockEntity e : entities) list.add(AlarmClockConverter.convert(e));
            return Resource.success(list);
        }).toObservable();
    }

    public Observable<Resource<AlarmClock>> addAlarmClock(AlarmClock alarmClock) {
        return Single.fromCallable(() -> {
            AlarmClockEntity entity = AlarmClockConverter.convert(alarmClock);
            return alarmClockDAO.addAlarmClock(entity);
        }).map(id -> {
            alarmClock.setId(id);
            return Resource.success(alarmClock);
        }).toObservable();
    }

    public Observable<Resource<AlarmClock>> removeAlarmClock(AlarmClock alarmClock) {
        return Single.fromCallable(() -> {
            alarmClockDAO.deleteAlarmClockById(alarmClock.getId());
            return true;
        }).map(res -> Resource.success(alarmClock)).toObservable();
    }

    public Observable<Resource<AlarmClock>> getAlarmClockById(long id) {
        return Single.fromCallable(() -> alarmClockDAO.getAlarmClockById(id))
                .map(entity -> Resource.success(AlarmClockConverter.convert(entity)))
                .toObservable();
    }
}
