package ba.unsa.etf.rma.cineaste

import org.junit.Test
import org.junit.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.hasProperty
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not

class UnitTests {
    @Test
    fun testGetFavoriteMovies(){
        val movies = getFavoriteMovies()
        assertEquals(movies.size, 6)
        assertThat(
            movies,
            hasItem<Movie>(
                hasProperty(
                    "title",
                    `is`("Pulp Fiction")
                )
            )
        )
        assertThat(
            movies,
            not(
                hasItem<Movie>(
                    hasProperty(
                        "title",
                        `is`("Black Widow")
                    )
                )
            )
        )
    }

    @Test
    fun testGetRecentMovies(){
        val movies = getRecentMovies()
        assertEquals(movies.size, 5)
        assertThat(
            movies,
            hasItem<Movie>(
                hasProperty(
                    "genre",
                    `is`("musical")
                )
            )
        )
        assertThat(
            movies,
            not(
                hasItem<Movie>(
                    hasProperty(
                        "genre",
                        `is`("crime")
                    )
                )
            )
        )
    }
}