package ba.unsa.etf.rma.cineaste.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ba.unsa.etf.rma.cineaste.data.ActorsMovies
import ba.unsa.etf.rma.cineaste.data.Movie

@Database(entities = [Movie::class, ActorsMovies::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun actorsMoviesDao(): ActorsMoviesDAO
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "cinaeste-db"
                    ).fallbackToDestructiveMigration(false)
                .build()
    }
}