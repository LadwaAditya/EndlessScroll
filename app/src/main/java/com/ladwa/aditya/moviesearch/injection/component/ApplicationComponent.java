package com.ladwa.aditya.moviesearch.injection.component;

import android.app.Application;
import android.content.Context;


import com.ladwa.aditya.moviesearch.data.DataManager;
import com.ladwa.aditya.moviesearch.injection.module.ApplicationModule;
import com.ladwa.aditya.moviesearch.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aditya on 09-Feb-17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();
}