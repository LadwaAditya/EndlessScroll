package com.ladwa.aditya.moviesearch.injection.component;



import com.ladwa.aditya.moviesearch.injection.module.FragmentModule;
import com.ladwa.aditya.moviesearch.injection.scope.PerFragment;

import dagger.Subcomponent;

/**
 * Created by Aditya on 09-Feb-17.
 */

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}