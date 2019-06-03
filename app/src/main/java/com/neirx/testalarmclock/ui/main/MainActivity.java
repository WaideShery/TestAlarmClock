package com.neirx.testalarmclock.ui.main;

import android.os.Bundle;

import com.neirx.testalarmclock.R;
import com.neirx.testalarmclock.databinding.ActivityMainBinding;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding b;
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);


    }
}
