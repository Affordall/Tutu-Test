package com.testapps.tututest.di.components;

import com.testapps.tututest.di.ActivityScope;
import com.testapps.tututest.di.modules.ChooserActivityModule;
import com.testapps.tututest.di.modules.DatabaseModule;
import com.testapps.tututest.views.ChooserActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = TutuTestAppComponent.class,
        modules = {ChooserActivityModule.class, DatabaseModule.class}
)
public interface IChooserActivityComponent {
    void inject(ChooserActivity chooserActivity);
}
