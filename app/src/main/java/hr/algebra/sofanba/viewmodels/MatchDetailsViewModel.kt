package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.Highlight
import kotlinx.coroutines.launch
import java.lang.Exception

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

}