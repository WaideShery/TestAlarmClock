package com.neirx.testalarmclock.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.databinding.ActivityMainBinding;
import com.neirx.testalarmclock.model.AlarmClock;
import com.neirx.testalarmclock.ui.create_alarm.EditAlarmActivity;
import com.neirx.testalarmclock.ui.main.adapter.AlarmClockAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainScreen.View{
    private ActivityMainBinding b;
    @Inject
    MainScreen.Presenter presenter;
    private AlarmClockAdapter alarmClockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);

        alarmClockAdapter = new AlarmClockAdapter();
        b.rvAlarmClocks.setLayoutManager(new LinearLayoutManager(this));
        b.rvAlarmClocks.setAdapter(alarmClockAdapter);

        b.btnCreateFirstAlarmClock.setOnClickListener(v->createAlarmClock());
        b.fabNewAlarmClock.setOnClickListener(v-> createAlarmClock());
        presenter.loadAlarmClocks();
    }

    @Override
    public void onAlarmClocksLoaded(List<AlarmClock> alarmClocks) {
        if (alarmClocks.isEmpty()){
            b.rvAlarmClocks.setVisibility(View.GONE);
            b.fabNewAlarmClock.hide();
            b.layEmptyContent.setVisibility(View.VISIBLE);
        } else {
            b.layEmptyContent.setVisibility(View.GONE);
            b.rvAlarmClocks.setVisibility(View.VISIBLE);
            b.fabNewAlarmClock.show();
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
