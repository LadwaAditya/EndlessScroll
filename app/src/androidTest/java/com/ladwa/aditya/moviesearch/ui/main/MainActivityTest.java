package com.ladwa.aditya.moviesearch.ui.main;

import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.TestApplication;
import com.ladwa.aditya.moviesearch.TestComponentRule;
import com.ladwa.aditya.moviesearch.data.remote.MovieSearchService;
import com.ladwa.aditya.moviesearch.data.remote.MovieSearchServiceFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.utils.RequestMatchers;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Aditya on 25-Mar-17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final int RESULT_OK = 200;

    private static final String PATH_MOVIE = "/movies.json";
    private static final String PATH_MOVIE_ZERO = "/movieszero.json";

    private final TestComponentRule mComponent = new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);
    @Rule public TestRule chain = RuleChain.outerRule(mComponent).around(activityTestRule);
    private MovieSearchService movieSearchService;

    @Before
    public void setUp() throws Exception {
        RESTMockServer.reset();
        movieSearchService = MovieSearchServiceFactory.makeMovieSearchService(RESTMockServer.getUrl());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldshowMovies_whenApiReturnsResults() throws Exception {
        RESTMockServer
                .whenGET(RequestMatchers.hasQueryParameters())
                .thenReturnFile(RESULT_OK, TestApplication.PATH_ASSETS_CONTACT + PATH_MOVIE);


        movieSearchService.getMovie("Batman", 1).toObservable();
        activityTestRule.launchActivity(null);

        typeTextToView(R.id.txt_search, "Batman");
        onView(withId(R.id.img_search)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).check(matches(isDisplayed()));
    }


    @Test
    public void shouldshowErrorMessage_whenApiReturnsNoResult() throws Exception {
        RESTMockServer
                .whenGET(RequestMatchers.hasQueryParameters())
                .thenReturnFile(RESULT_OK, TestApplication.PATH_ASSETS_CONTACT + PATH_MOVIE_ZERO);

        movieSearchService.getMovie("Batman", 1).toObservable();
        activityTestRule.launchActivity(null);

        typeTextToView(R.id.txt_search, "Batman");
        onView(withId(R.id.img_search)).perform(click());

        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).check(matches(isDisplayed()));

    }

    private void typeTextToView(@IdRes int viewId, String textToType) {
        onView(withId(viewId)).perform(typeText(textToType));
        closeSoftKeyboard();
    }
}