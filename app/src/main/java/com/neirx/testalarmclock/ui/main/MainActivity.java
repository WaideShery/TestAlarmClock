package com.neirx.testalarmclock.ui.main;

import android.os.Bundle;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.databinding.ActivityMainBinding;
import com.neirx.testalarmclock.model.AlarmClock;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainScreen.View{
    private ActivityMainBinding b;
    @Inject
    MainScreen.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);

        presenter.loadAlarmClocks();
    }

    @Override
    public void onAlarmClocksLoaded(List<AlarmClock> alarmClocks) {

    }

    @Override
    public void onAlarmClocksLoadedError(AppError appError) {

    }
}
