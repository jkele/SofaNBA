package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository
import hr.algebra.sofanba.database.model.SplashTeam
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Team
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application): AndroidViewModel(application)  {

    val teamsList = MutableLiveData<ArrayList<Team>>()
    private val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application)!!.nbaDao()
        repository = NbaRepository(nbaDao)
    }

    fun insertTeamsList(){
        viewModelScope.launch {
            val teamsList = Network().getNbaService().getTeams().data
            val splashTeams = ArrayList<SplashTeam>()

            teamsList.forEach { splashTeams.add(it.convertToSplashTeam()) }
            repository.insertTeamsList(splashTeams)
        }
    }

    fun getTeamsList() {
        val teams = ArrayList<Team>()
        repository.getTeamsList().forEach {
            teams.add(it.convertToTeam())
        }
        teamsList.value = teams
    }

    fun insertFavoriteTeam(team: Team) {
        repository.insertFavoriteTeam(team.convertToFavoriteTeam())
    }

}