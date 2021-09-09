package com.example.taskmaster;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
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
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.taskmaster", appContext.getPackageName());
    }



    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void MainActivityTest() {
        Espresso.onView(ViewMatchers.withId(R.id.addTaskBtn)).perform(click());
        Espresso.onView(withId(R.id.taskTitleediting)).perform(clearText(),typeText("test"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.editingDescription)).perform(clearText(),typeText("test"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.editState)).perform(clearText(),typeText("test"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.submitbtn)).perform(click());
        }

    @Test
    public void MainActivityTest2() {
        Espresso.onView(withId(R.id.addTaskBtn)).perform(click());
        Espresso.onView(withId(R.id.addText)).check(matches(withText("title task")));
  }

    @Test
    public void test3() {
        Espresso.onView(withId(R.id.settitngbtn)).perform(click());
        Espresso.onView(withId(R.id.titleForSettingPage)).check(matches(withText("Welcome ")));
    }
    @Test
    public void test4() {
        Espresso.onView(ViewMatchers.withId(R.id.hiUserName)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.heroimg)).check(matches(isDisplayed()));
    }

    @Test
    public void test5() {
        Espresso.onView(withId(R.id.recycleViewListtask)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("test")),click()));

    }
    @Test
    public void test6() {
        Espresso.onView(withId(R.id.addTaskBtn)).perform(click());
        Espresso.onView(withId(R.id.textView)).check(matches(withText("description task")));
    }
    @Test
    public void test7() {
        Espresso.onView(withId(R.id.addTaskBtn)).perform(click());
        Espresso.onView(withId(R.id.textView2)).check(matches(withText("TextView")));
    }
}