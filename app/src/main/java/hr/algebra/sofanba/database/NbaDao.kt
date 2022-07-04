package hr.algebra.sofanba.database

import androidx.room.*
import hr.algebra.sofanba.database.model.FavoritePlayer
import hr.algebra.sofanba.database.model.FavoriteTeam
import hr.algebra.sofanba.database.model.SplashTeam

@Dao
interface NbaDao {

    //Favorite player

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePlayer(player: FavoritePlayer)

    @Query("SELECT * FROM favoritePlayers")
    fun getFavoritePlayers(): List<FavoritePlayer>

    @Query("SELECT * FROM favoritePlayers")
    suspend fun getFavoritePlayersAsync(): List<FavoritePlayer>

    @Query("SELECT * FROM favoritePlayers WHERE id = :id")
    fun isPlayerFavorite(id: Int): Boolean

    @Delete
    fun deleteFavoritePlayer(player: FavoritePlayer)

    @Query("DELETE FROM favoritePlayers")
    fun deleteFavoritePlayers()


    //Favorite team

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTeam(team: FavoriteTeam)

    @Query("SELECT * FROM favoriteTeams")
    fun getFavoriteTeams(): List<FavoriteTeam>

    @Query("SELECT * FROM favoriteTeams")
    suspend fun getFavoriteTeamsAsync(): List<FavoriteTeam>

    @Query("SELECT * FROM favoriteTeams WHERE id = :id")
    fun isTeamFavorite(id: Int): Boolean

    @Delete
    fun deleteFavoriteTeam(team: FavoriteTeam)

    @Query("DELETE FROM favoriteTeams")
    fun deleteFavoriteTeams()

    //Splash screen

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamsList(teams: ArrayList<SplashTeam>)

    @Query("SELECT * FROM splashTeams")
    fun getTeamsList(): List<SplashTeam>

}