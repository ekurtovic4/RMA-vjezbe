package ba.unsa.etf.rma.cineaste.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActorsMovies(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "name") var actorName: String,
    @ColumnInfo(name = "movieId") var movieId: Long
)