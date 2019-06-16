package com.neirx.testalarmclock.app.error;

import static com.neirx.testalarmclock.app.error.AppError.UNDEFINED;


/**
 * Created by Waide Shery
 */
public class AppErrorFactory {

    public static AppError createFromThrowable(Throwable t) {

        return new AppError(UNDEFINED, t.getMessage(), t);
    }
}
