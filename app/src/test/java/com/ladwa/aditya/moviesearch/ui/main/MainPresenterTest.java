package com.ladwa.aditya.moviesearch.ui.main;

import com.ladwa.aditya.moviesearch.data.DataManager;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.util.RxSchedulersOverrideRule;
import com.ladwa.aditya.moviesearch.util.TestDataFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Aditya on 25-Mar-17.
 */
public class MainPresenterTest {

    private static final String SEARCH_TERM = "Batman";
    private static final int PAGE = 1;
    @Rule public final RxSchedulersOverrideRule mOverrideRule = new RxSchedulersOverrideRule();
    @Mock DataManager mockDataManager;
    @Mock MainContract.View mockView;
    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(mockDataManager);
        presenter.attachView(mockView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    @Test
    public void getMovies_shouldReturnResult() throws Exception {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTotalResults("300");
        movieResponse.setResponse("True");
        ArrayList<MovieResponse.Movie> movieArrayList = TestDataFactory.makMovieArrayList(5);
        movieResponse.setMovie(movieArrayList);

        when(mockDataManager.getMovies(SEARCH_TERM, PAGE)).thenReturn(Single.just(movieResponse));

        presenter.getMovies(SEARCH_TERM, PAGE);

        verify(mockView).showMovies(movieArrayList);
        verify(mockView, never()).showEmptyMovies(any(String.class));
        verify(mockView, never()).showError(any(Throwable.class));
    }


    @Test
    public void getMovies_shouldReturnNoResult() throws Exception {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTotalResults("300");
        movieResponse.setResponse("False");

        when(mockDataManager.getMovies(SEARCH_TERM, PAGE)).thenReturn(Single.just(movieResponse));

        presenter.getMovies(SEARCH_TERM, PAGE);

        verify(mockView).showEmptyMovies(any(String.class));
        verify(mockView, never()).showMovies(any(List.class));
        verify(mockView, never()).showError(any(Throwable.class));

    }


    @Test
    public void getMovies_shouldReturnError() throws Exception {
        when(mockDataManager.getMovies(SEARCH_TERM, PAGE)).thenReturn(Single.error(new RuntimeException()));

        presenter.getMovies(SEARCH_TERM, PAGE);

        verify(mockView).showError(any(Throwable.class));
        verify(mockView, never()).showMovies(any(List.class));
        verify(mockView, never()).showEmptyMovies(any(String.class));
    }
}