package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.response.SeasonStatsResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PlayerStatisticsViewModel: ViewModel() {

    val playerSeasonAveragesList = MutableLiveData<ArrayList<SeasonStatsResponse>>()

    fun getSeasonAveragesList(playerId: Int) {
        viewModelScope.launch {
            val seasonsList = ArrayList<Int>()
            for (i in 1979..2021) {
                seasonsList.add(i)
            }

            val asyncTasks = seasonsList.map { season ->
                async {
                    Network().getNbaService().getSeasonAverages(season, playerId)
                }
            }
            val responses = asyncTasks.awaitAll()
            playerSeasonAveragesList.value = responses as ArrayList<SeasonStatsResponse>
        }
    }

}