package com.testapps.tututest.di.components;

import android.content.Context;

import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.database.DatabaseHandler;
import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.database.tables.TableStation;
import com.testapps.tututest.di.modules.TutuTestAppModule;
import com.testapps.tututest.network.JsonParser;
import com.testapps.tututest.presenter.impl.ChooserActivityPresenterImpl;
import com.testapps.tututest.repository.impl.CityRepository;
import com.testapps.tututest.repository.impl.StationRepository;
import com.testapps.tututest.views.SettingsActivity;
import com.testapps.tututest.views.intro.FancyAppIntro;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                TutuTestAppModule.class
        })
public interface TutuTestAppComponent {

    Context context();

    void inject(SettingsActivity settingsActivity);
    void inject(FancyAppIntro fancyAppIntro);

    void inject(TutuTestApp app);
    void inject(JsonParser jsonParser);

    void inject(DatabaseHandler handler);

    void inject(ChooserActivityPresenterImpl chooserActivityPresenter);

    void inject(CityRepository cityRepository);
    void inject(StationRepository stationRepository);

}
