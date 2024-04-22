package ba.unsa.etf.rma.cineaste.data

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val releaseDate: String,
    var homepage: String?,
    var genre: String?,
    val posterPath: String
)