package com.aavash.jobfinder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginUI(){
        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .perform(ViewActions.typeText("aavashe@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.atvPasswordLog))
            .perform(ViewActions.typeText("password"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.testhome))
            .check(ViewAssertions.matches(ViewMatchers.withText("Find your dream jobs")))

    }

    @Test
    fun aboutUs(){

        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .perform(ViewActions.typeText("aavashe@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.atvPasswordLog))
            .perform(ViewActions.typeText("password"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
            .perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.icExtra))
            .perform((ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.btnMap))
            .check(ViewAssertions.matches(ViewMatchers.withText("Click to view Map")))


    }

    @Test
    fun APPLYjobs(){

        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .perform(ViewActions.typeText("aavashe@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.atvPasswordLog))
            .perform(ViewActions.typeText("password"))
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
            .perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.icJobs))
            .perform((ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.textView2))
            .check(ViewAssertions.matches(ViewMatchers.withText("My Applied Jobs")))


    }

    @Test
    fun profile(){

        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .perform(ViewActions.typeText("aavashe@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.atvPasswordLog))
            .perform(ViewActions.typeText("password"))
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
            .perform(ViewActions.click())

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.icProfile))
            .perform((ViewActions.click()))

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.testProfile))
            .check(ViewAssertions.matches(ViewMatchers.withText("Profile")))
        Thread.sleep(3000)

    }
    @Test
    fun signup(){



        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp))
            .perform(ViewActions.click())

        Thread.sleep(500)
        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp))
            .perform((ViewActions.click()))
        Thread.sleep(2000)
//        Espresso.onView(ViewMatchers.withId(R.id.btnlogout))
//                .check(ViewAssertions.matches(ViewMatchers.withText("LogOut")))
//        Thread.sleep(1000)

    }
    @Test
    fun InsideProfile(){

        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .perform(ViewActions.typeText("aavashe@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.atvPasswordLog))
            .perform(ViewActions.typeText("password"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
            .perform(ViewActions.click())

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.icProfile))
            .perform((ViewActions.click()))



        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.personalinfobtn))
            .check(ViewAssertions.matches(ViewMatchers.withText("User Info")))
        Thread.sleep(3000)

    }
    @Test
    fun unsuccesfull(){

        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .perform(ViewActions.typeText("aavashe@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.atvPasswordLog))
            .perform(ViewActions.typeText("password"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
            .perform(ViewActions.click())

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.icProfile))
            .perform((ViewActions.click()))



        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.atvEmailLog))
            .check(ViewAssertions.matches(ViewMatchers.withText("Wrong ID")))
        Thread.sleep(3000)

    }

}