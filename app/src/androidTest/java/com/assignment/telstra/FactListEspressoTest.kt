package com.assignment.telstra

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.assignment.telstra.ui.main.MainActivity
import com.assignment.telstra.ui.main.fact.FactListFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FactListEspressoTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun loadFragment() {
        activityRule.activity.loadFragment(FactListFragment())
    }
}