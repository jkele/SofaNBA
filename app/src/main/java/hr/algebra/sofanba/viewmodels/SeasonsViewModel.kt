package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.network.paging.season.SeasonMatchPagingSource
import kotlinx.coroutines.flow.Flow

class SeasonsViewModel: ViewModel() {

    val matchesList = MutableLiveData<ArrayList<Match>>()

    fun getSeasonMatchesFlow(season: Int, startDate: String, postseason: Boolean): Flow<PagingData<Match>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SeasonMatchPagingSource(Network().getNbaService(), season, startDate, postseason)
        }.flow.cachedIn(viewModelScope)
    }
}