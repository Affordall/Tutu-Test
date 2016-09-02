package com.testapps.tututest.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.testapps.tututest.app.TutuTestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                DatabaseModule.class,
                NetworkModule.class,
                TablesModule.class
        }
)
public class TutuTestAppModule {

    private final TutuTestApp app;

    public TutuTestAppModule(TutuTestApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

}
