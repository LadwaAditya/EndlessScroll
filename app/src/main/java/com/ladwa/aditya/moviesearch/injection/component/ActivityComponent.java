package com.ladwa.aditya.moviesearch.injection.component;


import com.ladwa.aditya.moviesearch.injection.module.ActivityModule;
import com.ladwa.aditya.moviesearch.injection.scope.PerActivity;
import com.ladwa.aditya.moviesearch.ui.base.BaseActivity;

import dagger.Subcomponent;

/**
 * Created by Aditya on 09-Feb-17.
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

}
