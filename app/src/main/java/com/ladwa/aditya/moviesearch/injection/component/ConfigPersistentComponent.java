package com.ladwa.aditya.moviesearch.injection.component;


import com.ladwa.aditya.moviesearch.injection.module.ActivityModule;
import com.ladwa.aditya.moviesearch.injection.module.FragmentModule;
import com.ladwa.aditya.moviesearch.injection.scope.ConfigPersistent;

import dagger.Component;

/**
 * Created by Aditya on 09-Feb-17.
 */

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

}
