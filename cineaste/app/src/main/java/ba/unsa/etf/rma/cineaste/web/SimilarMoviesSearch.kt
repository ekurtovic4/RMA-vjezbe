package ba.unsa.etf.rma.cineaste.web

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object SimilarMoviesSearch {
    private const val tmdb_api_key : String = "f90e0088c197afe4b8679e31c61e863b"

    suspend fun searchRequest(
        id: Long
    ): Result<List<String>>{
        return withContext(Dispatchers.IO) {
            try {
                val listSimilarMovies = arrayListOf<String>()
                val url1 =
                    "https://api.themoviedb.org/3/movie/$id/similar?api_key=$tmdb_api_key"
                val url = URL(url1)

                val httpConnection = url.openConnection()
                httpConnection.addRequestProperty("Authorization", "Bearer=$tmdb_api_key")

                (httpConnection as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use { it.readText() }
                    val jo = JSONObject(result)
                    val results = jo.getJSONArray("results")
                    for(i in 0..results.length()){
                        val movie = results.getJSONObject(i)
                        listSimilarMovies.add(movie.getString("title"))
                        if (i == 3) break
                    }
                }
                return@withContext Result.Success(listSimilarMovies);
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