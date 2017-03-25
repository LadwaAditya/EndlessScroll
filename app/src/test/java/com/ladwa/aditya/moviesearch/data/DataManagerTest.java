package com.ladwa.aditya.moviesearch.data;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.data.remote.MovieSearchService;
import com.ladwa.aditya.moviesearch.util.RxSchedulersOverrideRule;
import com.ladwa.aditya.moviesearch.util.TestDataFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 * Created by Aditya on 26-Mar-17.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class DataManagerTest {

    @Mock MovieSearchService mockMovieSearchService;
    @Rule public final RxSchedulersOverrideRule mOverrideRule = new RxSchedulersOverrideRule();

    private static final String SEARCH_STRING = "Batman";
    private static final int PAGE = 1;
    private DataManager dataManager;

    @Before
    public void setUp() throws Exception {
        dataManager = new DataManager(mockMovieSearchService);
    }

    @Test
    public void getMovieFromRemote_shouldReturnResults() throws Exception {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTotalResults("300");
        movieResponse.setResponse("True");
        ArrayList<MovieResponse.Movie> movieArrayList = TestDataFactory.makMovieArrayList(5);
        movieResponse.setMovie(movieArrayList);

        Mockito.when(mockMovieSearchService.getMovie("Batman", 1)).thenReturn(Single.just(movieResponse));

        dataManager.getMovies(SEARCH_STRING, PAGE).test()
                .assertComplete()
                .assertValue(movieResponse);
    }
}