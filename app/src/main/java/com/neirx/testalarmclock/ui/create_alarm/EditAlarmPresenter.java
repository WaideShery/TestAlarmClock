package com.neirx.testalarmclock.ui.create_alarm;

import com.neirx.testalarmclock.contract.ui.EditAlarmScreen;
import com.neirx.testalarmclock.data.repository.AlarmClockRepository;
import com.neirx.testalarmclock.ui.app_base.presenter.Presenter;

/**
 * Created by Waide Shery on 16.06.19.
 */
public class EditAlarmPresenter extends Presenter<EditAlarmScreen.View>
        implements EditAlarmScreen.Presenter {

    private AlarmClockRepository alarmClockRepository;

    public EditAlarmPresenter(EditAlarmScreen.View screen, AlarmClockRepository alarmClockRepository) {
        super(screen, screen);
        this.alarmClockRepository = alarmClockRepository;
    }

}
