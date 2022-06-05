package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.Highlight
import hr.algebra.sofanba.network.model.PlayerImage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class MatchDetailsViewModel: ViewModel() {

    val matchStatsList = MutableLiveData<ArrayList<GameStats>>()
    val matchHighlightsList = MutableLiveData<ArrayList<Highlight>>()
    val playerImagesList = MutableLiveData<ArrayList<ArrayList<PlayerImage>>>()

    fun getMatchStats(gameId: Int, perPage: Int, page: Int) {
        viewModelScope.launch {
            matchStatsList.value = Network().getNbaService().getStatsForMatch(gameId, perPage, page).data
        }
    }

    fun getMatchStatsAndPlayerImages(gameId: Int, perPage: Int, page: Int) {
        viewModelScope.launch {
            val matchStats = Network().getNbaService().getStatsForMatch(gameId, perPage, page).data
            val asyncTasks = matchStats.map { matchStat ->
                async {
                    try {
                        Network().getSofaService().getPlayerImages(matchStat.player.id).data
                    } catch (e: Exception) {
                        arrayListOf(PlayerImage(0, "", "", null))
                    }
                }
            }
            val responses = asyncTasks.awaitAll()

            playerImagesList.value = responses as ArrayList<ArrayList<PlayerImage>>
            matchStatsList.value = matchStats
        }
    }

    fun getMatchHighlights(gameId: Int) {
        viewModelScope.launch {
            try {
                matchHighlightsList.value = Network().getSofaService().getHighlightsForMatch(gameId).data
            } catch (e: Exception) {
                matchHighlightsList.value = arrayListOf()
            }
        }
    }

    fun addHighlightForMatch(requestBody: RequestBody) {
        viewModelScope.launch {
            Network().getSofaService().addHighlightForMatch(requestBody)
        }
    }

    fun deleteMatchHighlight(highlightId: Int) {
        viewModelScope.launch {
            Network().getSofaService().deleteMatchHighlight(highlightId)
        }
    }

}