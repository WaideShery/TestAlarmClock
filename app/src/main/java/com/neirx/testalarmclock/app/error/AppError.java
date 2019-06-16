package com.neirx.testalarmclock.app.error;

import androidx.annotation.Nullable;

/**
 * Created by Waide Shery
 */
public class AppError {
    public static final int UNDEFINED = 0;


    public final int type;
    public @Nullable final String message;
    public @Nullable final Throwable throwable;


    public AppError(int type) {
        this(type, null, null);
    }

    public AppError(int type, @Nullable String message) {
        this(type, message, null);

    }

    public AppError(int type, @Nullable Throwable throwable) {
        this(type, null, throwable);
    }

    public AppError(int type, @Nullable String message, @Nullable Throwable throwable) {
        this.type = type;
        this.message = message;
        this.throwable = throwable;
    }
}
