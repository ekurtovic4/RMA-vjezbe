package ba.unsa.etf.rma.cineaste.web

import ba.unsa.etf.rma.cineaste.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MovieRepository {
    //private val tmdb_api_key : String = BuildConfig.TMDB_API_KEY

    suspend fun getUpcomingMovies(
    ) : GetMoviesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getUpcomingMovies()
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun getFavoriteMovies(
    ) : GetMoviesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getFavoriteMovies()
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun searchMovie(
        query: String
    ) : GetMoviesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getMovie(query)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun getActors(
        id: Long
    ) : GetActorsResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getActors(id)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun getSimilarMovies(
        id: Long
    ) : GetMoviesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getSimilarMovies(id)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun getMovieDetails(
        id: Long
    ) : Movie?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getMovieDetails(id)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
}