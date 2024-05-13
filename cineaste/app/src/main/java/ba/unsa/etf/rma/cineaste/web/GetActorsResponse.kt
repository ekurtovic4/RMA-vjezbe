package ba.unsa.etf.rma.cineaste.web

import ba.unsa.etf.rma.cineaste.data.Actor
import com.google.gson.annotations.SerializedName

data class GetActorsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val actors: List<Actor>,
)