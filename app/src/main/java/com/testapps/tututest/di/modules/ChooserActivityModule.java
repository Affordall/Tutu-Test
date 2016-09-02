package com.testapps.tututest.di.modules;

import com.testapps.tututest.presenter.impl.ChooserActivityPresenterImpl;
import com.testapps.tututest.views.IChooserActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by affy on 30.08.16.
 */
@Module
public class ChooserActivityModule {

    private IChooserActivityView view;

    public ChooserActivityModule(IChooserActivityView view) {
        this.view = view;
    }

    @Provides
    public IChooserActivityView provideView() {
        return view;
    }

    @Provides
    public ChooserActivityPresenterImpl provideChooserActivityPresenterImpl (IChooserActivityView view){
        return  new ChooserActivityPresenterImpl(view);
    }
}
