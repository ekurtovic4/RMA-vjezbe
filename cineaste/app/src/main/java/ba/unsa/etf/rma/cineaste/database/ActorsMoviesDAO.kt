package ba.unsa.etf.rma.cineaste.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ba.unsa.etf.rma.cineaste.data.ActorsMovies

@Dao
interface ActorsMoviesDAO {
    @Query("SELECT * FROM actorsmovies")
    suspend fun getAll(): List<ActorsMovies>
    @Query("SELECT name FROM actorsmovies WHERE movieId LIKE :id")
    suspend fun getMovieActors(id: Long): List<String>
    @Insert
    suspend fun insertAll(vararg actorsMovies: ActorsMovies)
    @Delete
    suspend fun delete(actorsMovies: ActorsMovies)
}