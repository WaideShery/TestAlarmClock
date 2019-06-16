package com.neirx.testalarmclock.data.repository.base;


import com.neirx.testalarmclock.app.error.AppError;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.neirx.testalarmclock.data.repository.base.Status.ERROR;
import static com.neirx.testalarmclock.data.repository.base.Status.LOADING;
import static com.neirx.testalarmclock.data.repository.base.Status.SUCCESS;


public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final AppError error;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable AppError error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(AppError error, @Nullable T data) {
        return new Resource<>(ERROR, data, error);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public boolean isSuccess() {
        return status == SUCCESS && data != null;
    }

    public boolean isLoading() {
        return status == LOADING;
    }

    public boolean isLoaded() {
        return status != LOADING;
    }
}