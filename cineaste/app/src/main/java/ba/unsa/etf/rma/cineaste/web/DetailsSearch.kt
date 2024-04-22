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

object DetailsSearch {
    private const val tmdb_api_key : String = "f90e0088c197afe4b8679e31c61e863b"

    suspend fun searchRequest(
        id: Long
    ): Result<Movie>{
        return withContext(Dispatchers.IO) {
            try {
                var returnMovie = Movie(0,"Test","Test","Test","Test","Test", "Test")
                val url1 =
                    "https://api.themoviedb.org/3/movie/$id?api_key=$tmdb_api_key"
                val url = URL(url1)

                val httpConnection = url.openConnection()
                httpConnection.addRequestProperty("Authorization", "Bearer=$tmdb_api_key")

                (httpConnection as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use { it.readText() }
                    val jo = JSONObject(result)
                    val genre = jo.getJSONArray("genres").getJSONObject(0).getString("name")
                    val homepage = jo.getString("homepage")
                    returnMovie = Movie(0,"Test","Test","Test", homepage, genre, "Test")
                }
                return@withContext Result.Success(returnMovie);
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