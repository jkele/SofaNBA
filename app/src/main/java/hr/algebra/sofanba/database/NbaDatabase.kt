package hr.algebra.sofanba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.algebra.sofanba.database.model.FavoritePlayer

@Database(
    entities = [FavoritePlayer::class],
    version = 1
)
abstract class NbaDatabase : RoomDatabase() {
    abstract fun nbaDao(): NbaDao

    companion object {
        private var instance: NbaDatabase? = null

        fun getDatabase(context: Context): NbaDatabase? =
            Room.databaseBuilder(context, NbaDatabase::class.java, "NbaDatabase")
                .allowMainThreadQueries().build()
    }

}