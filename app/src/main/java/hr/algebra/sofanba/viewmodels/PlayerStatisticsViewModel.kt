package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.response.SeasonStatsResponse
import hr.algebra.sofanba.network.model.response.StatsResponse
import kotlinx.coroutines.launch

class PlayerStatisticsViewModel: ViewModel() {

    val playerSeasonAveragesList = MutableLiveData<ArrayList<SeasonStatsResponse>>()

    fun getPlayerSeasonAveragesList(playerId: Int) {
        viewModelScope.launch {
            val resultList = arrayListOf<SeasonStatsResponse>()
            val firstStats = Network().getNbaService().getPlayerSeason(playerId, false, 0)
            val lastStats = Network().getNbaService().getPlayerSeason(playerId, false, firstStats.meta.totalPages)

            val firstSeason = getMinSeason(firstStats)
            val lastSeason = getMaxSeason(lastStats)

            if (lastSeason != null && firstSeason != null) {
                if (lastSeason > firstSeason) {
                    for (i in lastSeason downTo lastSeason - 4 ){
                        resultList.add(Network().getNbaService().getSeasonAverages(i, playerId))
                    }
                } else {
                    for (i in lastSeason downTo firstSeason ){
                        resultList.add(Network().getNbaService().getSeasonAverages(i, playerId))
                    }
                }
            }
            playerSeasonAveragesList.value = resultList
        }
    }

    private fun getMaxSeason(statsResponse: StatsResponse): Int? {
        val seasonList = ArrayList<Int>()
        statsResponse.data.forEach {
            seasonList.add(it.game.season)
        }
        return seasonList.maxOrNull()
    }

    private fun getMinSeason(statsResponse: StatsResponse): Int? {
        val seasonList = ArrayList<Int>()
        statsResponse.data.forEach {
            seasonList.add(it.game.season)
        }
        return seasonList.minOrNull()
    }


}