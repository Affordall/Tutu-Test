package com.testapps.tututest.di.modules;

import android.app.Application;

import com.testapps.tututest.database.DatabaseHandler;
import com.testapps.tututest.repository.impl.CityRepository;
import com.testapps.tututest.repository.impl.StationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DatabaseModule {

    private DatabaseHandler databaseHandler;

    @Provides
    @Singleton
    DatabaseHandler provideDatabaseHandler(Application application) {
        this.databaseHandler = DatabaseHandler.getInstance(application);
        return databaseHandler;
    }

    @Provides
    @Singleton
    CityRepository provideCityRepository() {
        return new CityRepository(databaseHandler);
    }

    @Provides
    @Singleton
    StationRepository provideStationRepository() {
        return new StationRepository(databaseHandler);
    }

}
