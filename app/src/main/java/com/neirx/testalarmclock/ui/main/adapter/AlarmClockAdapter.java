package com.neirx.testalarmclock.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.databinding.AdapterAlarmClockListBinding;
import com.neirx.testalarmclock.model.AlarmClock;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class AlarmClockAdapter extends RecyclerView.Adapter<AlarmClockAdapter.AlarmClockHolder> {
    private List<AlarmClock> alarmClocks;
    private DataChangedHandler<AlarmClock> dataChangedHandler;

    public AlarmClockAdapter() {
        alarmClocks = new ArrayList<>();
        dataChangedHandler = new DataChangedHandler<>(this);
    }

    public void setAlarmClocks(List<AlarmClock> alarms) {
        dataChangedHandler.onDataChanged(alarmClocks, alarms);
    }

    @NonNull
    @Override
    public AlarmClockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_alarm_clock_list, parent, false);
        return new AlarmClockHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmClockHolder holder, int position) {
        holder.setAlarmClock(alarmClocks.get(position));
    }

    @Override
    public int getItemCount() {
        return alarmClocks.size();
    }

    class AlarmClockHolder extends RecyclerView.ViewHolder{
        AdapterAlarmClockListBinding b;

        AlarmClockHolder(@NonNull View itemView) {
            super(itemView);
            b = DataBindingUtil.bind(itemView);
        }

        private void setAlarmClock(AlarmClock alarmClock){

        }
    }
}
