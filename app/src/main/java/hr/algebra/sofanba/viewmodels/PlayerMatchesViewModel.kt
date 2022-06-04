package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.paging.playerMatch.PlayerMatchPagingSource
import kotlinx.coroutines.flow.Flow

class PlayerMatchesViewModel: ViewModel() {

    fun getPlayerMatchesFlow(season: Int, playerId: Int, postseason: Boolean): Flow<PagingData<GameStats>> {
        return Pager(PagingConfig(pageSize = 20)){
            PlayerMatchPagingSource(Network().getNbaService(), season, playerId, postseason)
        }.flow.cachedIn(viewModelScope)
    }

}