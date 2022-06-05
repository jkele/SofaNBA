package hr.algebra.sofanba.viewmodels

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

    fun getSeasonMatchesFlow(season: Int, postseason: Boolean): Flow<PagingData<Match>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SeasonMatchPagingSource(Network().getNbaService(), season, postseason)
        }.flow.cachedIn(viewModelScope)
    }
}