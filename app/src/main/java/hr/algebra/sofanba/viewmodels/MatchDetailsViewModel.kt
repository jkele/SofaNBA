package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.Highlight
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class MatchDetailsViewModel: ViewModel() {

    val matchStatsList = MutableLiveData<ArrayList<GameStats>>()
    val matchHighlightsList = MutableLiveData<ArrayList<Highlight>>()

    fun getMatchStats(gameId: Int, perPage: Int, page: Int) {
        viewModelScope.launch {
            matchStatsList.value = Network().getNbaService().getStatsForMatch(gameId, perPage, page).data
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