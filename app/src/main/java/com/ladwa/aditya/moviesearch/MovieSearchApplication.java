package com.ladwa.aditya.moviesearch;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.ladwa.aditya.moviesearch.injection.component.ApplicationComponent;
import com.ladwa.aditya.moviesearch.injection.component.DaggerApplicationComponent;
import com.ladwa.aditya.moviesearch.injection.module.ApplicationModule;

import timber.log.Timber;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieSearchApplication extends Application {
    ApplicationComponent mApplicationComponent;
    private static MovieSearchApplication sMoviewMovieSearchApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sMoviewMovieSearchApplication = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }
    }

    public static MovieSearchApplication get(Context context) {
        return (MovieSearchApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this,BuildConfig.MOVIE_API_URL))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public static synchronized MovieSearchApplication getInstance() {
        return sMoviewMovieSearchApplication;
    }
}
