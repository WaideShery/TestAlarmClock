package com.neirx.testalarmclock.ui.create_alarm;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.contract.ui.EditAlarmScreen;
import com.neirx.testalarmclock.databinding.ActivityEditAlarmBinding;
import com.neirx.testalarmclock.ui.app_base.binding.WeekBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dagger.android.AndroidInjection;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class EditAlarmActivity extends AppCompatActivity implements EditAlarmScreen.View{
    private ActivityEditAlarmBinding b;
    @Inject EditAlarmScreen.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        b = DataBindingUtil.setContentView(this, R.layout.activity_edit_alarm);

        initTimePickers();
        initWeek();
    }

    private void initTimePickers(){
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 24; i++) data.add(String.format(Locale.getDefault(), "%02d", i));
        b.hourPicker.setData(data);

        data = new ArrayList<>();
        for (int i = 0; i < 60; i++) data.add(String.format(Locale.getDefault(), "%02d", i));
        b.minutePicker.setData(data);
    }

    private void initWeek() {
        b.week.chbDay1.setOnCheckedChangeListener(this::onWeekDaySelected);
        b.week.chbDay2.setOnCheckedChangeListener(this::onWeekDaySelected);
        b.week.chbDay3.setOnCheckedChangeListener(this::onWeekDaySelected);
        b.week.chbDay4.setOnCheckedChangeListener(this::onWeekDaySelected);
        b.week.chbDay5.setOnCheckedChangeListener(this::onWeekDaySelected);
        b.week.chbDay6.setOnCheckedChangeListener(this::onWeekDaySelected);
        b.week.chbDay7.setOnCheckedChangeListener(this::onWeekDaySelected);
    }

    private void onWeekDaySelected(CompoundButton c, boolean b) {
        WeekBinding.onDayCheckedChanged(c, b);
    }
}
