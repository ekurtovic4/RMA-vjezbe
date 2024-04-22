package ba.unsa.etf.rma.cineaste.web

import ba.unsa.etf.rma.cineaste.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object MovieRepository {
    private const val tmdb_api_key : String = "f90e0088c197afe4b8679e31c61e863b"

    suspend fun searchRequest(
        query: String
    ): Result<List<Movie>>{
        return withContext(Dispatchers.IO) {
            try {
                val movies = arrayListOf<Movie>()
                val url1 =
                    "https://api.themoviedb.org/3/search/movie?api_key=$tmdb_api_key&query=$query"
                val url = URL(url1)

                val httpConnection = url.openConnection()
                httpConnection.addRequestProperty("Authorization", "Bearer=$tmdb_api_key")

                (httpConnection as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use { it.readText() }
                    val jo = JSONObject(result)
                    val results = jo.getJSONArray("results")
                    for (i in 0 until results.length()) {
                        val movie = results.getJSONObject(i)
                        val title = movie.getString("original_title")
                        val id = movie.getInt("id")
                        val posterPath = movie.getString("poster_path")
                        val overview = movie.getString("overview")
                        val releaseDate = movie.getString("release_date")
                        movies.add(Movie(id.toLong(), title, overview, releaseDate, null, null, posterPath))
                        if (i == 5) break
                    }
                }
                return@withContext Result.Success(movies);
            }
            catch (e: MalformedURLException) {
                return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
            } catch (e: IOException) {
                return@withContext Result.Error(Exception("Cannot read stream"))
            } catch (e: JSONException) {
                return@withContext Result.Error(Exception("Cannot parse JSON"))
            }

        }
    }
}