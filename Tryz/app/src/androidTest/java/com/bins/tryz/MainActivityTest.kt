package com.bins.tryz

import android.content.Intent
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.bins.tryz.server.MockServer
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var mockServer: MockWebServer

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() = mockServer.shutdown()

    @Test
    fun successCase() {
        mockServer.dispatcher = MockServer.ResponseDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        rule.launchActivity(intent)

        SystemClock.sleep(2000)
        //  error view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.noNetowrkScreen))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewData)).inRoot(RootMatchers.withDecorView(Matchers.`is`(rule.getActivity().getWindow().getDecorView())))
            .check(ViewAssertions.matches(matchTextWithPosition(1, Matchers.allOf(ViewMatchers.withId(R.id.mainITemView),
                isDisplayed()),"square/simplerrd")))

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewData)).inRoot( RootMatchers.withDecorView( Matchers.`is`(rule.getActivity().getWindow().getDecorView())))
            .check(ViewAssertions.matches(matchTextWithPosition(0,
                Matchers.allOf(ViewMatchers.withId(R.id.mainITemView), isDisplayed()),"square/yajl-objc")))
    }

    @Test
    fun failureCase() {
        mockServer.dispatcher = MockServer.ErrorDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        rule.launchActivity(intent)
        //  failure layout visible
        Espresso.onView(ViewMatchers.withId(R.id.noNetowrkScreen))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    fun matchTextWithPosition(position: Int, itemMatcher: Matcher<View?>,expected :String): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: org.hamcrest.Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(position)
                return  ((viewHolder?.itemView as ViewGroup).getChildAt(1) as TextView).text.toString().equals(expected,ignoreCase = true)
            }
        }
    }

}