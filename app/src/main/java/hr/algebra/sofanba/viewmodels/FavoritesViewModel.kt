package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.PlayerImage
import hr.algebra.sofanba.network.model.Team
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoritesViewModel(application: Application): AndroidViewModel(application) {

    val favoritePlayers = MutableLiveData<ArrayList<Player>>()
    val favoriteTeams = MutableLiveData<ArrayList<Team>>()
    val playerImages = MutableLiveData<ArrayList<PlayerImage>>()

    val testPlayerImages = MutableLiveData<ArrayList<ArrayList<PlayerImage>>>()

    private val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application)!!.nbaDao()
        repository = NbaRepository(nbaDao)
    }

    fun getFavoritePlayersAndImages() {
        viewModelScope.launch {
            val players = ArrayList<Player>()
            repository.getFavoritePlayersAsync().forEach {
                players.add(it.convertToPlayer())
            }

            val asyncTasks = players.map { player ->
                async {
                    try {
                        Network().getSofaService().getPlayerImages(player.id).data
                    } catch (e: Exception) {
                        arrayListOf(PlayerImage(0, "", "", null))
                    }
                }
            }
            val response = asyncTasks.awaitAll()

            favoritePlayers.value = players
            testPlayerImages.value = response as ArrayList<ArrayList<PlayerImage>>
        }
    }

    fun getFavoritePltestayersAndImages() {
        viewModelScope.launch {
            val players = ArrayList<Player>()
            val images = ArrayList<PlayerImage>()
            repository.getFavoritePlayersAsync().forEach {
                players.add(it.convertToPlayer())
            }
            players.forEach {
                try {
                    images.addAll(Network().getSofaService().getPlayerImages(it.id).data)
                } catch (e: Exception) {}
            }
            favoritePlayers.value = players
            playerImages.value = images
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