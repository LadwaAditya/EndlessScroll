package com.ladwa.aditya.moviesearch.ui.main;

import com.ladwa.aditya.moviesearch.data.DataManager;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.injection.scope.ConfigPersistent;
import com.ladwa.aditya.moviesearch.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Aditya on 25-Mar-17.
 */
@ConfigPersistent
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override public void attachView(MainContract.View mvpView) {
        super.attachView(mvpView);
        checkViewAttached();
        getMvpView().setUpView();
    }

    @Override public void detachView() {
        super.detachView();

    }

    @Override public void getMovies(String search, int page) {
        checkViewAttached();
        addDisposable(dataManager.getMovies(search, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MovieResponse>() {
                    @Override public void onSuccess(MovieResponse movieResponse) {
                        if (movieResponse.getResponse().equalsIgnoreCase("True")) {
                            getMvpView().showMovies(movieResponse.getMovie());
                        } else {
                            getMvpView().showEmptyMovies("No movies found");
                        }
                    }

                    @Override public void onError(Throwable e) {
                        getMvpView().showError(e);
                    }
                }));
    }
}
