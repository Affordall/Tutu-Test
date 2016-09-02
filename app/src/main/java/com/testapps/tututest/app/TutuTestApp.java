package com.testapps.tututest.app;

import android.app.Application;
import android.content.Context;

import com.testapps.tututest.BuildConfig;
import com.testapps.tututest.di.components.DaggerTutuTestAppComponent;
import com.testapps.tututest.di.components.TutuTestAppComponent;
import com.testapps.tututest.di.modules.TutuTestAppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;


public class TutuTestApp extends Application {

    private TutuTestAppComponent appComponent;
    private static Application instance;
    private static RealmConfiguration realmConfiguration;

    public static TutuTestApp get(Context context) {
        return (TutuTestApp) context.getApplicationContext();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        //buildRealm();
        buildGraphAndInject();
    }

    public TutuTestAppComponent getAppComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerTutuTestAppComponent.builder()
                .tutuTestAppModule(new TutuTestAppModule(this))
                .build();
        appComponent.inject(this);
    }

    private void buildRealm() {
         realmConfiguration = new RealmConfiguration.Builder(this)
                .name("tutudb.realm")
                //.modules(new ModelsRealmModule())
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RealmConfiguration getRealmConfiguration() {
        return realmConfiguration;
    }
}
