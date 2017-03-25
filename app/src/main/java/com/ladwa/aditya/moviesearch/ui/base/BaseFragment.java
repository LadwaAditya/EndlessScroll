package com.ladwa.aditya.moviesearch.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ladwa.aditya.moviesearch.MovieSearchApplication;
import com.ladwa.aditya.moviesearch.injection.component.ConfigPersistentComponent;
import com.ladwa.aditya.moviesearch.injection.component.DaggerConfigPersistentComponent;
import com.ladwa.aditya.moviesearch.injection.component.FragmentComponent;
import com.ladwa.aditya.moviesearch.injection.module.FragmentModule;

import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;

/**
 * Created by Aditya on 09-Feb-17.
 */
public abstract class BaseFragment extends Fragment {

    private static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    private static final LongSparseArray<ConfigPersistentComponent> sComponentsArray =
            new LongSparseArray<>();
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private FragmentComponent mFragmentComponent;
    private long mFragmentId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mFragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_FRAGMENT_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (sComponentsArray.get(mFragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mFragmentId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MovieSearchApplication.get(
                            getActivity()).getComponent())
                    .build();
            sComponentsArray.put(mFragmentId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mFragmentId);
            configPersistentComponent = sComponentsArray.get(mFragmentId);
        }
        mFragmentComponent = configPersistentComponent.fragmentComponent(new FragmentModule(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        return view;
    }

    public abstract int getLayout();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId);
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mFragmentId);
            sComponentsArray.remove(mFragmentId);
        }
        super.onDestroy();
    }

    public FragmentComponent fragmentComponent() {
        return mFragmentComponent;
    }
}
