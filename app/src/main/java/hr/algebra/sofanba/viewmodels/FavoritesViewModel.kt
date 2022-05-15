package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository
import hr.algebra.sofanba.database.model.FavoritePlayer
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.Team
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application): AndroidViewModel(application) {

    val favoritePlayers = MutableLiveData<ArrayList<Player>>()
    val favoriteTeams = MutableLiveData<ArrayList<Team>>()

    private val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application).nbaDao()
        repository = NbaRepository(nbaDao)
    }

    fun getFavoritePlayers() {
        viewModelScope.launch {
            val players = ArrayList<Player>()
            repository.getFavoritePlayersAsync().forEach {
                players.add(it.convertToPlayer())
            }
            favoritePlayers.value = players
        }
    }

    fun deleteFavoritePlayer(player: Player) {
        repository.deleteFavoritePlayer(player.convertToFavoritePlayer())
    }

    fun getFavoriteTeams() {
        viewModelScope.launch {
            val teams = ArrayList<Team>()
            repository.getFavoriteTeamsAsync().forEach {
                teams.add(it.convertToTeam())
            }
            favoriteTeams.value = teams
        }
    }

    fun deleteFavoriteTeam(team: Team) {
        repository.deleteFavoriteTeam(team.convertToFavoriteTeam())
    }
}