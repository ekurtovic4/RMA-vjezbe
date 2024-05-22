package ba.unsa.etf.rma.cineaste.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ba.unsa.etf.rma.cineaste.data.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>
    @Insert
    suspend fun insertAll(vararg movies: Movie)
    @Delete
    suspend fun delete(movie: Movie)
}