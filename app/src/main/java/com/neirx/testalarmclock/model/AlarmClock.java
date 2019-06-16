package com.neirx.testalarmclock.model;

/**
 * Created by Waide Shery on 03.06.19.
 */
public class AlarmClock {
    private long id;
    private boolean isEnabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
