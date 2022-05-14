package hr.algebra.sofanba.database

import androidx.room.*
import hr.algebra.sofanba.database.model.FavoritePlayer
import hr.algebra.sofanba.network.model.Player

@Dao
interface NbaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePlayer(player: FavoritePlayer)

    @Query("SELECT * FROM favoritePlayers")
    fun getFavoritePlayers(): List<FavoritePlayer>

    @Query("SELECT * FROM favoritePlayers")
    suspend fun getFavoritePlayersAsync(): List<FavoritePlayer>

    @Delete
    fun deleteFavoritePlayer(player: FavoritePlayer)
}