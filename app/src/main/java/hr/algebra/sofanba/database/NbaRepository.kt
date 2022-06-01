package hr.algebra.sofanba.database

import hr.algebra.sofanba.database.model.FavoritePlayer
import hr.algebra.sofanba.database.model.FavoriteTeam

class NbaRepository(private val nbaDao: NbaDao) {

    //Favorite player

    fun getFavoritePlayers(): List<FavoritePlayer> {
        return nbaDao.getFavoritePlayers()
    }

    suspend fun getFavoritePlayersAsync(): List<FavoritePlayer> {
        return nbaDao.getFavoritePlayersAsync()
    }

    fun insertFavoritePlayer(player: FavoritePlayer) {
        nbaDao.insertFavoritePlayer(player)
    }

    fun isPlayerFavorite(id: Int): Boolean{
        return nbaDao.isPlayerFavorite(id)
    }

    fun deleteFavoritePlayer(player: FavoritePlayer) {
        nbaDao.deleteFavoritePlayer(player)
    }

    fun deleteFavoritePlayers() {
        nbaDao.deleteFavoritePlayers()
    }

    //Favorite team

    fun getFavoriteTeams(): List<FavoriteTeam> {
        return nbaDao.getFavoriteTeams()
    }

    suspend fun getFavoriteTeamsAsync(): List<FavoriteTeam> {
        return nbaDao.getFavoriteTeamsAsync()
    }

    fun isTeamFavorite(id: Int): Boolean {
        return nbaDao.isTeamFavorite(id)
    }

    fun insertFavoriteTeam(team: FavoriteTeam) {
        nbaDao.insertFavoriteTeam(team)
    }

    fun deleteFavoriteTeam(team: FavoriteTeam) {
        nbaDao.deleteFavoriteTeam(team)
    }

    fun deleteFavoriteTeams() {
        nbaDao.deleteFavoriteTeams()
    }
}