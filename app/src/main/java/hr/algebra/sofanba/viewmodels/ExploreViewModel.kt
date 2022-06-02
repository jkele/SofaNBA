package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.PlayerImage
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.network.paging.player.PlayerPagingSource
import kotlinx.coroutines.launch

class ExploreViewModel(application: Application): AndroidViewModel(application) {

    val playerImages = MutableLiveData<ArrayList<PlayerImage>>()
    val teamsList = MutableLiveData<ArrayList<Team>>()
    val favoritePlayers = MutableLiveData<ArrayList<Player>>()
    val favoriteTeams = MutableLiveData<ArrayList<Team>>()

    private val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application).nbaDao()
        repository = NbaRepository(nbaDao)
    }

    val flow = Pager(PagingConfig(pageSize = 20)){
        PlayerPagingSource(Network().getNbaService())
    }.flow.cachedIn(viewModelScope)

    fun getTeamsList(){
        viewModelScope.launch {
            teamsList.value = Network().getNbaService().getTeams().data
        }
    }


    fun insertFavoritePlayer(player: Player) {
        repository.insertFavoritePlayer(player.convertToFavoritePlayer())
    }

    fun deleteFavoritePlayer(player: Player) {
        repository.deleteFavoritePlayer(player.convertToFavoritePlayer())
    }

    fun getFavoritePlayers() {
        val players = ArrayList<Player>()
        repository.getFavoritePlayers().forEach {
            players.add(it.convertToPlayer())
        }
        favoritePlayers.value = players
    }

    fun getFavoriteTeams() {
        val teams = ArrayList<Team>()
        repository.getFavoriteTeams().forEach {
            teams.add(it.convertToTeam())
        }
        favoriteTeams.value = teams
    }

    fun insertFavoriteTeam(team: Team) {
        repository.insertFavoriteTeam(team.convertToFavoriteTeam())
    }

    fun deleteFavoriteTeam(team: Team) {
        repository.deleteFavoriteTeam(team.convertToFavoriteTeam())
    }
}