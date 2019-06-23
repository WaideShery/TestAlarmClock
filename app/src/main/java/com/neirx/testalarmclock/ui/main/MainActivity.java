package com.neirx.testalarmclock.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.model.AlarmClock;
import com.neirx.testalarmclock.ui.create_alarm.EditAlarmActivity;
import com.neirx.testalarmclock.ui.main.adapter.AlarmClockAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainScreen.View{
    private MainBinding b;
    @Inject
    MainScreen.Presenter presenter;
    private AlarmClockAdapter alarmClockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        b = new MainBinding(this, R.layout.activity_main);
        Timber.tag("TTag").d("onCreate()");

        alarmClockAdapter = new AlarmClockAdapter();
        b.res().rvAlarmClocks.setLayoutManager(new LinearLayoutManager(this));
        b.res().rvAlarmClocks.setAdapter(alarmClockAdapter);

        b.res().btnCreateFirstAlarmClock.setOnClickListener(v->createAlarmClock());
        b.res().fabNewAlarmClock.setOnClickListener(v-> createAlarmClock());
        presenter.loadAlarmClocks();
    }

    @Override
    public void onAlarmClocksLoaded(List<AlarmClock> alarmClocks) {
        Timber.tag("TTag").d("onAlarmClocksLoaded() "+alarmClocks.size());
        alarmClockAdapter.setAlarmClocks(alarmClocks);
        if (alarmClocks.isEmpty()){
            b.res().rvAlarmClocks.setVisibility(View.GONE);
            b.res().fabNewAlarmClock.hide();
            b.res().layEmptyContent.setVisibility(View.VISIBLE);
        } else {
            b.res().layEmptyContent.setVisibility(View.GONE);
            b.res().rvAlarmClocks.setVisibility(View.VISIBLE);
            b.res().fabNewAlarmClock.show();
        }
    }

    @Override
    public void onAlarmClocksLoadedError(AppError appError) {

    }

    private void createAlarmClock() {
        Intent intent = new Intent(this, EditAlarmActivity.class);
        startActivity(intent);
    }
}
