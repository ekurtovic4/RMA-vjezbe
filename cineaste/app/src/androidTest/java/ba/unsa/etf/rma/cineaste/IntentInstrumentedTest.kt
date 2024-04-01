package ba.unsa.etf.rma.cineaste

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Test

class IntentInstrumentedTest {
    @Test
    fun testDetailActivityInstantiation(){
        val pokreniDetalje = Intent(ApplicationProvider.getApplicationContext(),MovieDetailActivity::class.java)
        pokreniDetalje.putExtra("movie_title","Pulp Fiction")
        launchActivity<MovieDetailActivity>(pokreniDetalje)
        onView(withId(R.id.movie_title)).check(matches(withText("Pulp Fiction")))
        onView(withId(R.id.movie_genre)).check(matches(withText("crime")))
        onView(withId(R.id.movie_overview)).check(
            matches(
                withSubstring(
                    "pair of diner bandits"
                )
            )
        )
        onView(withId(R.id.movie_poster)).check(matches(withImage(R.drawable.crime)))
    }

    private fun withImage(@DrawableRes id: Int) = object : TypeSafeMatcher<View>(){
        override fun describeTo(description: Description) {
            description.appendText("Drawable does not contain image with id: $id")
        }

        override fun matchesSafely(item: View): Boolean {
            val context: Context = item.context
            val bitmap: Bitmap? = context.getDrawable(id)?.toBitmap()
            return item is ImageView && item.drawable.toBitmap().sameAs(bitmap)
        }

    }

    @Test
    fun testLinksIntent(){
        Intents.init()
        val pokreniDetalje = Intent(ApplicationProvider.getApplicationContext(),MovieDetailActivity::class.java)
        pokreniDetalje.putExtra("movie_title","Pulp Fiction")
        launchActivity<MovieDetailActivity>(pokreniDetalje)
        onView(withId(R.id.movie_website)).perform(click())
        Intents.intended(hasAction(Intent.ACTION_VIEW))
        Intents.release()
    }
}