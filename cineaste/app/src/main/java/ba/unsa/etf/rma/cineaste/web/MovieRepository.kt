package ba.unsa.etf.rma.cineaste.web

import android.content.Context
import ba.unsa.etf.rma.cineaste.data.ActorsMovies
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.database.AppDatabase
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

    suspend fun getFavoriteMovies(context: Context) : List<Movie> {
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            var movies = db!!.movieDao().getAll()
            return@withContext movies
        }
    }

    suspend fun writeFavorite(context: Context,movie:Movie) : String?{
        return withContext(Dispatchers.IO) {
            try{
                var db = AppDatabase.getInstance(context)
                db!!.movieDao().insertAll(movie)
                return@withContext "success"
            }
            catch(error:Exception){
                return@withContext null
            }
        }
    }

    suspend fun deleteFavorite(context: Context, movie: Movie) : String?{
        return withContext(Dispatchers.IO) {
            try{
                var db = AppDatabase.getInstance(context)
                db!!.movieDao().delete(movie)
                return@withContext "success"
            }
            catch(error:Exception){
                return@withContext null
            }
        }
    }

    suspend fun getActors(context: Context, movieId: Long) : List<String>{
        return withContext(Dispatchers.IO) {
            try{
                var db = AppDatabase.getInstance(context)
                val actors = db!!.actorsMoviesDao().getMovieActors(movieId)
                return@withContext actors
            }
            catch(error:Exception){
                return@withContext emptyList()
            }
        }
    }

    suspend fun writeActors(context: Context, actor: ActorsMovies) : String?{
        return withContext(Dispatchers.IO) {
            try{
                var db = AppDatabase.getInstance(context)
                db!!.actorsMoviesDao().insertAll(actor)
                return@withContext "success"
            }
            catch(error:Exception){
                return@withContext null
            }
        }
    }

    suspend fun deleteActors(context: Context, actor: ActorsMovies) : String?{
        return withContext(Dispatchers.IO) {
            try{
                var db = AppDatabase.getInstance(context)
                db!!.actorsMoviesDao().delete(actor)
                return@withContext "success"
            }
            catch(error:Exception){
                return@withContext null
            }
        }
    }
}