package com.neirx.testalarmclock.ui.create_alarm;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.app.error.AppError;
import com.neirx.testalarmclock.contract.ui.EditAlarmScreen;
import com.neirx.testalarmclock.model.AlarmClock;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class EditAlarmActivity extends AppCompatActivity implements EditAlarmScreen.View, EditAlarmBinding.OnAlarmChangedListener {
    public static final String EXTRA_ALARM_CLOCK_ID = "alarm_clock_id";
    @Inject EditAlarmScreen.Presenter presenter;
    private AlarmClock oldAlarmClock;
    private AlarmClock newAlarmClock;
    private EditAlarmBinding b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        b = new EditAlarmBinding(this, R.layout.activity_edit_alarm);
        b.setOnAlarmChangedListener(this);
        setSupportActionBar(b.res().toolbar);

        long id = getIntent().getLongExtra(EXTRA_ALARM_CLOCK_ID, -1);
        if (id == -1) {
            onAlarmLoaded(new AlarmClock());
        } else {
            presenter.loadAlarm(id);
        }
    }

    @Override
    public void onWeekDaySelected(int index, boolean b) {
        newAlarmClock.weekDays()[index] = b;
    }

    @Override
    public void onDescriptionChanged(String s) {
        newAlarmClock.setDescription(s);
    }

    @Override
    public void onTimeChanged(int time) {
        newAlarmClock.setTimeInMinute(time);
    }

    @Override
    public void onAlarmLoaded(AlarmClock alarmClock) {
        oldAlarmClock = alarmClock;
        newAlarmClock = oldAlarmClock.copy();
        b.setAlarmClock(oldAlarmClock);
    }

    @Override
    public void onAlarmLoadedError(AppError appError) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.tag("TTag").d("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.edit_alarm_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.miDone:
                saveAlarmClock();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveAlarmClock() {
        presenter.saveAlarm(newAlarmClock);
    }

    @Override
    public void onAlarmSavedSuccessful() {
        finish();
    }

    @Override
    public void onAlarmSavedError(AppError appError) {

    }

    @Override
    public void onBackPressed() {
        if (newAlarmClock.equals(oldAlarmClock)) {
            super.onBackPressed();
        } else {
            showDeleteChangesPopup();
        }
    }

    private void showDeleteChangesPopup() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.delete_alarm_clock_changes)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.remove, (dialogInterface, i) -> {
                    super.onBackPressed();
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) presenter.stop();
        super.onDestroy();
    }
}
