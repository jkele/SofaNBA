package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.network.paging.match.TeamMatchPagingSource
import kotlinx.coroutines.flow.Flow

class TeamMatchesViewModel: ViewModel() {

    fun getMatchesFlow(teamId: Int, season: Int, postSeason:Boolean): Flow<PagingData<Match>> {
        return Pager(PagingConfig(pageSize = 20)){
            TeamMatchPagingSource(Network().getNbaService(), teamId, season, postSeason)
        }.flow.cachedIn(viewModelScope)
    }


}