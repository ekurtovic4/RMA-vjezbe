package ba.unsa.etf.rma.cineaste

import android.app.SearchManager
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import androidx.test.espresso.assertion.PositionAssertions.isPartiallyAbove
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

class MovieDetInstrumentedTest {
    @Test
    fun testDetailActivityInstantiation(){
        val pokreniDetalje = Intent(ApplicationProvider.getApplicationContext(),MovieDetailActivity::class.java)
        pokreniDetalje.putExtra("movie_title","Zootopia")
        launchActivity<MovieDetailActivity>(pokreniDetalje)
        onView(withId(R.id.movie_backdrop)).check(isPartiallyAbove(withId(R.id.movie_poster)))
        onView(withId(R.id.movie_poster)).check(isCompletelyLeftOf(withId(R.id.movie_title)))
        onView(withId(R.id.movie_overview)).check(isCompletelyBelow(withId(R.id.movie_backdrop)))
    }

    @Test
    fun testLinksIntent(){
        Intents.init()
        val pokreniDetalje = Intent(ApplicationProvider.getApplicationContext(),MovieDetailActivity::class.java)
        pokreniDetalje.putExtra("movie_title","Zootopia")
        launchActivity<MovieDetailActivity>(pokreniDetalje)
        onView(withId(R.id.movie_title)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasExtra(SearchManager.QUERY, "Zootopia trailer"))
        Intents.release()
    }
}














