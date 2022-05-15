package hr.algebra.sofanba.database

import androidx.room.*
import hr.algebra.sofanba.database.model.FavoritePlayer
import hr.algebra.sofanba.database.model.FavoriteTeam

@Dao
interface NbaDao {

    //Favorite player

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePlayer(player: FavoritePlayer)

    @Query("SELECT * FROM favoritePlayers")
    fun getFavoritePlayers(): List<FavoritePlayer>

    @Query("SELECT * FROM favoritePlayers")
    suspend fun getFavoritePlayersAsync(): List<FavoritePlayer>

    @Delete
    fun deleteFavoritePlayer(player: FavoritePlayer)


    //Favorite team

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTeam(team: FavoriteTeam)

    @Query("SELECT * FROM favoriteTeams")
    fun getFavoriteTeams(): List<FavoriteTeam>

    @Query("SELECT * FROM favoriteTeams")
    suspend fun getFavoriteTeamsAsync(): List<FavoriteTeam>

    @Delete
    fun deleteFavoriteTeam(team: FavoriteTeam)


}