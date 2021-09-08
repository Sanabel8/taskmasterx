package com.example.taskmaster;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.taskmaster", appContext.getPackageName());
    }

    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void test1() {
        onView(withText("My tasks")).check(matches(isDisplayed()));
    }

    @Test
    public void test2() {
        Espresso.onView(withId(R.id.heroimg)).check(matches(isDisplayed()));
    }

    @Test
    public void test3() {
        Espresso.onView(withId(R.id.settitngbtn)).perform(click());
        Espresso.onView(withId(R.id.titleForSettingPage)).check(matches(ViewMatchers.withText("Welcome ")));

    }
    @Test
    public void test4() {
//        onView(ViewMatchers.withId(R.id.addTaskBtn)).check(matches(isDisplayed()));
//      addText   onView(ViewMatchers.withId(R.id.home2)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.addTaskBtn)).perform(click());
        Espresso.onView(withId(R.id.addText)).check(matches(ViewMatchers.withText("title task")));



    }
    @Test
    public void test5() {
        onView(withId(R.id.hiUserName)).check(matches(isDisplayed()));
    }
    @Test
    public void test6() {
        Espresso.onView(ViewMatchers.withId(R.id.recycleViewListtask)).check(matches(isDisplayed()));    }
}