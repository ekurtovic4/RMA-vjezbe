package ba.unsa.etf.rma.cineaste.web

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object ActorsSearch {
    private const val tmdb_api_key : String = "f90e0088c197afe4b8679e31c61e863b"

    suspend fun searchRequest(
        id: Long
    ): Result<List<String>>{
        return withContext(Dispatchers.IO) {
            try {
                val listActors = arrayListOf<String>()
                val url1 =
                    "https://api.themoviedb.org/3/movie/$id/credits?api_key=$tmdb_api_key"
                val url = URL(url1)

                val httpConnection = url.openConnection()
                httpConnection.addRequestProperty("Authorization", "Bearer=$tmdb_api_key")

                (httpConnection as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use { it.readText() }
                    val jo = JSONObject(result)
                    val cast = jo.getJSONArray("cast")
                    for(i in 0..cast.length()){
                        val actor = cast.getJSONObject(i)
                        listActors.add(actor.getString("name"))
                        if (i == 3) break
                    }
                }
                return@withContext Result.Success(listActors);
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