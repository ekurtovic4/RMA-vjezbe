package ba.unsa.etf.rma.cineaste.web

import ba.unsa.etf.rma.cineaste.data.Movie
import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)