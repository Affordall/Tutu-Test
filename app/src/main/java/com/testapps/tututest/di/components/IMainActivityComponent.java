package com.testapps.tututest.di.components;


import com.testapps.tututest.di.ActivityScope;
import com.testapps.tututest.di.modules.MainActivityModule;
import com.testapps.tututest.views.MainActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = TutuTestAppComponent.class,
        modules = MainActivityModule.class
)
public interface IMainActivityComponent {
    void inject(MainActivity activity);
}
