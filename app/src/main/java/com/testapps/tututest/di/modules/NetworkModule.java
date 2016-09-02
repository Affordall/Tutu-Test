package com.testapps.tututest.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient.Builder()
                .build();

        return okHttpClient;
    }
}
