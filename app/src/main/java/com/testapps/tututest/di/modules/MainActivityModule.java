package com.testapps.tututest.di.modules;


import com.testapps.tututest.presenter.impl.MainActivityPresenterImpl;
import com.testapps.tututest.views.IMainActivityView;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    private IMainActivityView view;

    public MainActivityModule(IMainActivityView view) {
        this.view = view;
    }

    @Provides
    public IMainActivityView provideView() {
        return view;
    }

    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl (IMainActivityView view){
        return  new MainActivityPresenterImpl(view);
    }
}
