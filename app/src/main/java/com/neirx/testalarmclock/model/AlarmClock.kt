package com.neirx.testalarmclock.model

/**
 * Created by Waide Shery on 16.06.19.
 */
data class AlarmClock(
        var id: Long = 0,
        var isEnabled: Boolean = false,
        //from 0 to 1439
        var description: String = "",
        //from 0 to 1439
        var timeInMinute: Int = 0,
        //monday ... sunday
        private var weekDays: BooleanArray = booleanArrayOf(false, false, false, false, false, false, false)) {


    fun copy(): AlarmClock {
        return copy(id = id, isEnabled = isEnabled, description = description,
                timeInMinute = timeInMinute, weekDays = weekDays.clone());
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AlarmClock

        if (id != other.id) return false
        if (isEnabled != other.isEnabled) return false
        if (timeInMinute != other.timeInMinute) return false
        if (!description.equals(other.description)) return false
        if (!weekDays.contentEquals(other.weekDays)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + isEnabled.hashCode()
        result = 31 * result + timeInMinute
        result = 31 * result + weekDays.contentHashCode()
        return result
    }

    /**
     * Array with enabled (true) or disabled(false) for alarm clock week days.
     * 0 - monday
     * ...
     * 6 - sunday
     */
    fun weekDays(): BooleanArray {
        return weekDays;
    }
}