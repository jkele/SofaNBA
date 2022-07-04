package hr.algebra.sofanba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hr.algebra.sofanba.database.converters.TeamTypeConverter
import hr.algebra.sofanba.database.model.FavoritePlayer
import hr.algebra.sofanba.database.model.FavoriteTeam
import hr.algebra.sofanba.database.model.SplashTeam

@Database(
    entities = [FavoritePlayer::class, FavoriteTeam::class, SplashTeam::class],
    version = 1
)
@TypeConverters(TeamTypeConverter::class)
abstract class NbaDatabase : RoomDatabase() {
    abstract fun nbaDao(): NbaDao

    companion object {
        private var instance: NbaDatabase? = null

        fun getDatabase(context: Context): NbaDatabase? {
            if (instance == null) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context): NbaDatabase =
            Room.databaseBuilder(context, NbaDatabase::class.java, "NbaDatabase")
                .allowMainThreadQueries().build()
    }

}