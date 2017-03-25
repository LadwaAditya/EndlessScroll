package com.ladwa.aditya.moviesearch.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Toast;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.databinding.ActivityMainBinding;
import com.ladwa.aditya.moviesearch.ui.adapter.MoviePagerAdapter;
import com.ladwa.aditya.moviesearch.ui.base.BaseActivity;

import io.reactivex.subjects.PublishSubject;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private ActivityMainBinding mBinding;
    private PublishSubject<String> mPublishSubject = PublishSubject.create();

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

    public PublishSubject<String> getPublishSubject() {
        return mPublishSubject;
    }

    @Override public void onTabSelected(TabLayout.Tab tab) {
        mBinding.pager.setCurrentItem(tab.getPosition());
    }

    @Override public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override public void onTabReselected(TabLayout.Tab tab) {

    }


    public void onClickSearch(View view) {
        Toast.makeText(this, mBinding.txtSearch.getText().toString(), Toast.LENGTH_SHORT).show();
        mPublishSubject.onNext(mBinding.txtSearch.getText().toString());
    }
}
