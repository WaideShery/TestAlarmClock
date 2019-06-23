package com.neirx.testalarmclock.ui.create_alarm;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.aigestudio.wheelpicker.WheelPicker;
import com.neirx.testalarmclock.databinding.ActivityEditAlarmBinding;
import com.neirx.testalarmclock.model.AlarmClock;
import com.neirx.testalarmclock.ui.app_base.binding.Binding;
import com.neirx.testalarmclock.ui.app_base.binding.WeekBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Waide Shery on 19.06.19.
 */
public class EditAlarmBinding extends Binding<ActivityEditAlarmBinding> implements TextWatcher {
    public interface OnAlarmChangedListener{
        /**
         * @param day index 0 - moday ... 6 - sunday
         */
        void onWeekDaySelected(int day, boolean isSelected);
        void onDescriptionChanged(String description);
        void onTimeChanged(int time);
    }
    private OnAlarmChangedListener onAlarmChangedListener;
    private List<CheckBox> weekCheckBoxes;

    public void setOnAlarmChangedListener(OnAlarmChangedListener onAlarmChangedListener) {
        this.onAlarmChangedListener = onAlarmChangedListener;
    }

    public EditAlarmBinding(Activity activity, int layoutId) {
        super(activity, layoutId);
        weekCheckBoxes = new ArrayList<>(Arrays.asList(b.week.chbDay1, b.week.chbDay2,
                b.week.chbDay3, b.week.chbDay4, b.week.chbDay5, b.week.chbDay6, b.week.chbDay7));
        initTimePickers();
        b.etDescription.addTextChangedListener(this);
        b.hourPicker.setOnItemSelectedListener(this::onHourChanged);
        b.minutePicker.setOnItemSelectedListener(this::onMinuteChanged);
    }

    private void initTimePickers() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 24; i++) data.add(String.format(Locale.getDefault(), "%02d", i));
        b.hourPicker.setData(data);

        data = new ArrayList<>();
        for (int i = 0; i < 60; i++) data.add(String.format(Locale.getDefault(), "%02d", i));
        b.minutePicker.setData(data);
    }

    private void weekDayCheckedListener(CompoundButton.OnCheckedChangeListener listener) {
        for (CheckBox ch : weekCheckBoxes) {
            ch.setOnCheckedChangeListener(listener);
        }
    }

    private void onWeekDayChecked(CompoundButton checkBox, boolean isChecked){
        int index = weekCheckBoxes.indexOf(checkBox);
        WeekBinding.onDayCheckedChanged(checkBox, isChecked);
        updateOneTimeVisibility();
        if (onAlarmChangedListener != null){
            onAlarmChangedListener.onWeekDaySelected(index, isChecked);
        }
    }

    public void setAlarmClock(AlarmClock alarmClock) {
        b.etDescription.setText(alarmClock.getDescription());
        weekDayCheckedListener(null);
        for (int i = 0; i < alarmClock.weekDays().length; i++) {
            weekCheckBoxes.get(i).setChecked(alarmClock.weekDays()[i]);
        }
        weekDayCheckedListener(this::onWeekDayChecked);
        updateOneTimeVisibility();

    }

    private void updateOneTimeVisibility(){
        boolean hasCheckedDay = false;
        for (CheckBox ch : weekCheckBoxes){
            if (ch.isChecked()){
                hasCheckedDay = true;
                break;
            }
        }
        if (hasCheckedDay) b.tvOneTime.setVisibility(View.GONE);
        else b.tvOneTime.setVisibility(View.VISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (onAlarmChangedListener != null){
            onAlarmChangedListener.onDescriptionChanged(s.toString());
        }
    }

    private void onHourChanged(WheelPicker wheelPicker, Object o, int i) {
        onTimeChanged();
    }

    private void onMinuteChanged(WheelPicker wheelPicker, Object o, int i) {
        onTimeChanged();
    }

    private void onTimeChanged(){
        String hours = (String) b.hourPicker.getData().get(b.hourPicker.getCurrentItemPosition());
        String minutes = (String) b.minutePicker.getData().get(b.minutePicker.getCurrentItemPosition());
        int hoursValue = Integer.parseInt(hours);
        int minutesValue = Integer.parseInt(minutes);
        if (onAlarmChangedListener != null){
            onAlarmChangedListener.onTimeChanged(hoursValue*60+minutesValue);
        }
    }
}
