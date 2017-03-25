package com.ladwa.aditya.moviesearch.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.ui.base.BaseFragment;

import timber.log.Timber;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieFragment extends BaseFragment {

    public static Fragment newInstance() {
        return new MovieFragment();
    }

    @Override public int getLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
        ((MainActivity) getActivity()).getPublishSubject().subscribe(s -> Timber.d(s));
    }
}
