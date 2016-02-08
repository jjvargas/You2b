package com.chilliwifi.you2b;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
      new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureRecyclerViewIsPopulated() {
        // Type text and then press the button.
        try {
            Thread.sleep(2000);
            onView(withId(R.id.recyclerView)).check(recyclerViewIsPopulated());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private ViewAssertion recyclerViewIsPopulated() {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (!(view instanceof RecyclerView)) {
                    throw noViewFoundException;
                }
                RecyclerView rv = (RecyclerView) view;
                int recysize = rv.getAdapter().getItemCount();
                System.out.println("Number of child: " + recysize);
                Assert.assertTrue(recysize > 0);
            }
        };
    }

} 
