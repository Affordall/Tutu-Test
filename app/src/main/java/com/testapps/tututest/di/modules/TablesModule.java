package com.testapps.tututest.di.modules;

import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.database.tables.TableStation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by affy on 01.09.16.
 */
@Module
public final class TablesModule {

    @Provides
    @Singleton
    TableCity provideTableCity() {
        return new TableCity();
    }

    @Provides
    @Singleton
    TableStation provideTableStation() {
        return new TableStation();
    }

}
