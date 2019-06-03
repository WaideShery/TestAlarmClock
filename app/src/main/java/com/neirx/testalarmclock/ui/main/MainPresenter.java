package com.neirx.testalarmclock.ui.main;

import com.neirx.testalarmclock.contract.ui.MainScreen;
import com.neirx.testalarmclock.ui.app_base.presenter.Presenter;

/**
 * Created by Waide Shery on 03.06.19.
 */
public class MainPresenter extends Presenter<MainScreen.View>
        implements MainScreen.Presenter {

    public MainPresenter(MainScreen.View screen) {
        super(screen, screen);
    }

}

