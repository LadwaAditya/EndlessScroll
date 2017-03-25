package com.ladwa.aditya.moviesearch.ui.main;

import com.ladwa.aditya.moviesearch.util.RxSchedulersOverrideRule;
import com.ladwa.aditya.moviesearch.util.TestDataFactory;
import com.ladwa.aditya.moviesearch.data.DataManager;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;

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

    @Mock DataManager mockDataManager;
    @Mock MainContract.View mockView;
    @Rule public final RxSchedulersOverrideRule mOverrideRule = new RxSchedulersOverrideRule();


    private MainPresenter presenter;

    private static final String SEARCH_TERM = "Batman";
    private static final int PAGE = 1;

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
        verify(mockView, never()).showError(any(String.class));
    }


    @Test
    public void getMovies_shouldReturnNoResult() throws Exception {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTotalResults("300");
        movieResponse.setResponse("False");

        when(mockDataManager.getMovies(SEARCH_TERM, PAGE)).thenReturn(Single.just(movieResponse));

        presenter.getMovies(SEARCH_TERM, PAGE);

        verify(mockView,never()).showMovies(any(List.class));
        verify(mockView).showError(any(String.class));
    }
}