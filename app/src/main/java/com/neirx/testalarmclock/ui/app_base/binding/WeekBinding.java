package com.neirx.testalarmclock.ui.app_base.binding;

import android.content.res.Resources;
import android.widget.CompoundButton;

import com.neirx.testalarmclock.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by Waide Shery
 */
public class WeekBinding {
    public static void onDayCheckedChanged(CompoundButton checkBox, boolean isChecked) {
        Resources res = checkBox.getResources();
        int color = isChecked ? res.getColor(R.color.week_day_checked_text_color)
                : res.getColor(R.color.week_day_unchecked_text_color);
        checkBox.setTextColor(color);
    }

    private static int getFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getFirstDayOfWeek();
    }

    public static String getDayName(int dayIndex) {
        DateFormatSymbols symbols = DateFormatSymbols.getInstance();

        if (getFirstDayOfWeek() == Calendar.SUNDAY) {
            dayIndex += 1;
        } else {
            if (dayIndex == 6) dayIndex = 1;
            else dayIndex += 2;
        }

        String day = symbols.getShortWeekdays()[dayIndex];
        return day.substring(0, 1).toUpperCase() + day.substring(1);
    }

}
