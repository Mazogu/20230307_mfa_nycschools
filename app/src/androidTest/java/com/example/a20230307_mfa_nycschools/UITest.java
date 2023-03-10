package com.example.a20230307_mfa_nycschools;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.a20230307_mfa_nycschools.view.MainActivity;
import com.example.a20230307_mfa_nycschools.view.NYCSchoolListFragment;
import com.example.a20230307_mfa_nycschools.viewmodel.IdleResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import timber.log.Timber;

@RunWith(AndroidJUnit4.class)
public class UITest {

    private ActivityScenario<MainActivity> activity;

    @Before
    public void startActivity() {
        activity = ActivityScenario.launch(MainActivity.class);
        IdlingRegistry.getInstance().register(IdleResource.getResourceInstance());
    }
    @Test
    public void checkInitialState(){
        onView(withId(R.id.schoolList)).check(matches(not(isDisplayed())));
    }

    @Test
    public void findSchool(){
        onView(withId(R.id.schoolList)).perform(RecyclerViewActions.scrollToPosition(5));
        onView(withText("Liberation Diploma Plus High School")).perform(click());
        onView(withId(R.id.sat_title)).check(matches(isDisplayed()));
        onView(withId(R.id.sat_title)).check(matches(withText("LIBERATION DIPLOMA PLUS")));
    }

    @Test
    public void findSchoolWithNoScores(){
        onView(withId(R.id.schoolList)).perform(RecyclerViewActions.scrollToPosition(5));
        onView(withText("Epic High School - South")).perform(click());
        onView(withId(R.id.sat_title)).check(matches(isDisplayed()));
        onView(withId(R.id.sat_title)).check(matches(withText("School Not Found")));
    }




    @After
    public void closeActivity(){
        activity.close();
        IdlingRegistry.getInstance().unregister(IdleResource.getResourceInstance());
    }
}
