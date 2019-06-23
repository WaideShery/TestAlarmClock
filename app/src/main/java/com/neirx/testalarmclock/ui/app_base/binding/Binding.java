package com.neirx.testalarmclock.ui.app_base.binding;

import android.app.Activity;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;

/**
 * Created by Waide Shery on 19.06.19.
 */
public abstract class Binding <T> {
    final protected T b;

    public Binding (Activity activity, @LayoutRes int layoutId) {
        b = (T) DataBindingUtil.setContentView(activity, layoutId);
    }

    public T res(){
        return b;
    }
}
