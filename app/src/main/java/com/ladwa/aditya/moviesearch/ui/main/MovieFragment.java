package com.ladwa.aditya.moviesearch.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.databinding.FragmentMovieBinding;
import com.ladwa.aditya.moviesearch.ui.adapter.EndlessRecyclerViewScrollListener;
import com.ladwa.aditya.moviesearch.ui.adapter.MovieListAdapter;
import com.ladwa.aditya.moviesearch.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieFragment extends BaseFragment implements MainContract.View {

    @Inject MainPresenter presenter;
    private FragmentMovieBinding movieBinding;
    private ArrayList<MovieResponse.Movie> mMovieList;
    private MovieListAdapter movieListAdapter;
    private static final String KEY_TAB = "tab";
    private int mTab = 0;
    private String searchQuery;

    public static Fragment newInstance(int tab) {
        Bundle bundle = new Bundle();
        MovieFragment movieFragment = new MovieFragment();
        switch (tab) {
            case 0:
                bundle.putInt(KEY_TAB, tab);
                movieFragment.setArguments(bundle);
                return movieFragment;
            case 1:
                bundle.putInt(KEY_TAB, tab);
                movieFragment.setArguments(bundle);
                return movieFragment;
        }
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mTab = getArguments().getInt(KEY_TAB);
        movieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        Timber.d(String.valueOf(mTab));
        return movieBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
        presenter.attachView(this);
    }

    @Override public void setUpView() {
        mMovieList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(mMovieList);
        if (mTab == 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            movieBinding.rvMovie.setLayoutManager(linearLayoutManager);
            movieBinding.rvMovie.setAdapter(movieListAdapter);
            movieBinding.rvMovie.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    presenter.getMovies(searchQuery, page + 1);
                    showProgressBar();
                }
            });
        } else if (mTab == 1) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            movieBinding.rvMovie.setLayoutManager(gridLayoutManager);
            movieBinding.rvMovie.setAdapter(movieListAdapter);
            movieBinding.rvMovie.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
                @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    presenter.getMovies(searchQuery, page + 1);
                    showProgressBar();
                }
            });
        }
        ((MainActivity) getActivity()).getPublishSubject().subscribe(integerStringPair -> {
            if (integerStringPair.first == mTab) {
                searchQuery = integerStringPair.second;
                movieBinding.progressBar.setVisibility(View.VISIBLE);
                presenter.getMovies(integerStringPair.second, 1);
            }
        });


    }

    @Override public void showError(Throwable error) {
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
        hidePrograssBar();
    }


    private void hidePrograssBar() {
        movieBinding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void showProgressBar() {
        movieBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void showMovies(List<MovieResponse.Movie> movieList) {
        mMovieList.addAll(movieList);
        movieListAdapter.notifyDataSetChanged();
        hidePrograssBar();
    }

    @Override public void showEmptyMovies(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
        hidePrograssBar();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
