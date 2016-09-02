package com.testapps.tututest.presenter.impl;

import com.testapps.tututest.presenter.IMainActivityPresenter;
import com.testapps.tututest.views.IMainActivityView;

import javax.inject.Inject;

public class MainActivityPresenterImpl implements IMainActivityPresenter {

    private IMainActivityView view;

    @Inject
    public MainActivityPresenterImpl(IMainActivityView view) {
        this.view = view;
    }

}
