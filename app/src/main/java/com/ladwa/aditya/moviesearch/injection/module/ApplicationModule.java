package com.ladwa.aditya.moviesearch.injection.module;

import android.app.Application;
import android.content.Context;

import com.ladwa.aditya.moviesearch.injection.scope.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 09-Feb-17.
 */

@Module
public class ApplicationModule {

    protected final Application mApplication;
    private final String mBaseUrl;

    public ApplicationModule(Application mApplication, String mBaseUrl) {
        this.mApplication = mApplication;
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }


}
