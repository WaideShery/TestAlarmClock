package com.neirx.testalarmclock.ui.app_base.presenter;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Waide Shery on 21.11.2018.
 */
public abstract class Presenter<T> {
    private CompositeDisposable disposables = new CompositeDisposable();
    protected LifecycleOwner lifecycleOwner;
    protected T screen;

    protected boolean screenReady() {
        return lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED;
    }

    public Presenter(T screen, LifecycleOwner lifecycleOwner) {
        this.screen = screen;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void stop() {
        disposables.clear();
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
