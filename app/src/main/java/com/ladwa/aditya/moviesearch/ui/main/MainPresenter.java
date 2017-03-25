package com.ladwa.aditya.moviesearch.ui.main;

import com.ladwa.aditya.moviesearch.data.DataManager;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.injection.scope.ConfigPersistent;
import com.ladwa.aditya.moviesearch.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

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
                .toObservable()
                .flatMap(movieResponse -> Observable.fromArray(movieResponse.getMovie()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<MovieResponse.Movie>>() {
                    @Override public void onNext(List<MovieResponse.Movie> movieList) {
                        Timber.d(movieList.get(0).getTitle());
                    }

                    @Override public void onError(Throwable e) {
                        Timber.d(e,"Error");
                    }

                    @Override public void onComplete() {

                    }
                }));
    }
}
