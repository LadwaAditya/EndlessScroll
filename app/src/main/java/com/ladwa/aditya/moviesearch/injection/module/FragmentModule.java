package com.ladwa.aditya.moviesearch.injection.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.ladwa.aditya.moviesearch.injection.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 09-Feb-17.
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment providesFragment() {
        return mFragment;
    }

    @Provides
    Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mFragment.getActivity();
    }

}
