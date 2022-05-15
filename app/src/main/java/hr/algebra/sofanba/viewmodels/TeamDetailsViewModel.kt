package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Team
import kotlinx.coroutines.launch

class TeamDetailsViewModel(application: Application): AndroidViewModel(application) {

    val teamsList = MutableLiveData<ArrayList<Team>>()
    val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application).nbaDao()
        repository = NbaRepository(nbaDao)
    }

    fun getTeamsList() {
        viewModelScope.launch {
            teamsList.value = Network().getNbaService().getTeams().data
        }
    }

    fun insertFavoriteTeam(team: Team) {
        repository.insertFavoriteTeam(team.convertToFavoriteTeam())
    }

    fun isTeamFavorite(id: Int): Boolean {
        return repository.isTeamFavorite(id)
    }

    fun deleteFavoriteTeam(team: Team) {
        repository.deleteFavoriteTeam(team.convertToFavoriteTeam())
    }

}