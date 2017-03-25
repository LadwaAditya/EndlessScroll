package com.ladwa.aditya.moviesearch;

import com.ladwa.aditya.moviesearch.injection.component.ApplicationComponent;
import com.ladwa.aditya.moviesearch.injection.component.DaggerApplicationComponent;
import com.ladwa.aditya.moviesearch.injection.module.ApplicationModule;

import io.appflate.restmock.RESTMockServer;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class TestApplication extends MovieSearchApplication {

    public static final String PATH_ASSETS_CONTACT = "movie";

    @Override
    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this, RESTMockServer.getUrl()))
                    .build();
        }
        return mApplicationComponent;
    }
}
