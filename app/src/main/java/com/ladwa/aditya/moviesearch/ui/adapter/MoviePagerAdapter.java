package com.ladwa.aditya.moviesearch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ladwa.aditya.moviesearch.ui.main.MovieFragment;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private final Integer TOTAL_TABS = 2;
    private final String TAB_TITLE_ONE = "TAB 1";
    private final String TAB_TITLE_TWO = "TAB 2";

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MovieFragment.newInstance();
            case 1:
                return MovieFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return TAB_TITLE_ONE;
            case 1:
                return TAB_TITLE_TWO;
        }
        return super.getPageTitle(position);
    }
}
