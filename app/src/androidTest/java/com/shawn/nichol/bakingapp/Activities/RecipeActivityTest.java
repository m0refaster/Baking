package com.shawn.nichol.bakingapp.Activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.shawn.nichol.bakingapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);

    @Before
    public void recipeRegisterIdlingResource() {
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void recipeRecyclerViewLoaded() {
        onView(withId(R.id.rv_recipe)).check(matches(isDisplayed()));
    }

    @Test
    public void recipeRecyclerCard() {
        ViewInteraction frameLayout =
                onView(allOf(withId(R.id.card_container), childAtPosition(allOf(withId(R.id.rv_recipe),
                        childAtPosition(withId(R.id.recipe_place_holder), 0)), 0), isDisplayed()));

        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void recipeRecyclerCardText() {
        ViewInteraction textView =
                onView(allOf(withId(R.id.recipe_tv), withText("Nutella Pie"), childAtPosition(allOf(withId(R.id.card_container),
                        childAtPosition(withId(R.id.rv_recipe), 0)), 0), isDisplayed()));

        textView.check(matches(withText("Nutella Pie")));
    }

    @Test
    public void recipeRecyclerView(){
        ViewInteraction recyclerView =
                onView(allOf(withId(R.id.rv_recipe), childAtPosition(allOf(withId(R.id.recipe_place_holder),
                        childAtPosition(withId(android.R.id.content), 0)), 0), isDisplayed()));

        recyclerView.check(matches(isDisplayed()));
    }

    @Test
    public void recipeRecyclerViewItem_Click(){
        // TODO no Idea why this fails? Please include a solution to this problem.
        //SystemClock.sleep(3000);
        //Espresso.onData((withId(R.id.rv_recipe))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onData(anything()).inAdapterView(withId(R.id.rv_recipe)).atPosition(1).perform(click());
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
