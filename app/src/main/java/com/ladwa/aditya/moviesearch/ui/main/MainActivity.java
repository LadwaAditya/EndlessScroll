package com.ladwa.aditya.moviesearch.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.databinding.ActivityMainBinding;
import com.ladwa.aditya.moviesearch.ui.adapter.MoviePagerAdapter;
import com.ladwa.aditya.moviesearch.ui.base.BaseActivity;

import io.reactivex.subjects.PublishSubject;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {


    private ActivityMainBinding mBinding;
    private PublishSubject<Pair<Integer, String>> mPublishSubject = PublishSubject.create();
    private Integer currentTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.pager.setAdapter(new MoviePagerAdapter(getSupportFragmentManager()));
        mBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mBinding.tabLayout.setupWithViewPager(mBinding.pager);
        mBinding.tabLayout.addOnTabSelectedListener(this);
    }

    public PublishSubject<Pair<Integer, String>> getPublishSubject() {
        return mPublishSubject;
    }

    @Override public void onTabSelected(TabLayout.Tab tab) {
        mBinding.pager.setCurrentItem(tab.getPosition());
        currentTab = tab.getPosition();
    }

    @Override public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override public void onTabReselected(TabLayout.Tab tab) {

    }


    public void onClickSearch(View view) {
        String searchQuery = mBinding.txtSearch.getText().toString().trim();
        if (searchQuery.length() > 0) {
            mPublishSubject.onNext(new Pair<>(currentTab, searchQuery));
        } else {
            Toast.makeText(this, R.string.error_no_movie_entered, Toast.LENGTH_SHORT).show();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
