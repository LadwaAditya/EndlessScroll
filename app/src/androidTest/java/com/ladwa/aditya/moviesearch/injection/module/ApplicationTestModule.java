package com.ladwa.aditya.moviesearch.injection.module;

import android.app.Application;
import android.content.Context;


import com.ladwa.aditya.moviesearch.data.remote.MovieSearchService;
import com.ladwa.aditya.moviesearch.data.remote.MovieSearchServiceFactory;
import com.ladwa.aditya.moviesearch.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 13-Feb-17.
 */
@Module
public class ApplicationTestModule {
    private final Application mApplication;
    private final String mBaseUrl;

    public ApplicationTestModule(Application application, String baseUrl) {
        mApplication = application;
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    MovieSearchService providesMovieService() {
        return MovieSearchServiceFactory.makeMovieSearchService(mBaseUrl);
    }

}
