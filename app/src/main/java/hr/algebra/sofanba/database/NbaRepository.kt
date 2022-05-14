package hr.algebra.sofanba.database

import hr.algebra.sofanba.database.model.FavoritePlayer

class NbaRepository(private val nbaDao: NbaDao) {

    fun getFavoritePlayers(): List<FavoritePlayer> {
        return nbaDao.getFavoritePlayers()
    }

    suspend fun getFavoritePlayersAsync(): List<FavoritePlayer> {
        return nbaDao.getFavoritePlayersAsync()
    }

    fun insertFavoritePlayer(player: FavoritePlayer) {
        nbaDao.insertFavoritePlayer(player)
    }

    fun deleteFavoritePlayer(player: FavoritePlayer) {
        nbaDao.deleteFavoritePlayer(player)
    }
}