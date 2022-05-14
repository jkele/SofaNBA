package hr.algebra.sofanba.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.PlayerImage
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.network.paging.PlayerPagingSource
import kotlinx.coroutines.launch

class ExploreViewModel: ViewModel() {

    val playerImages = MutableLiveData<ArrayList<PlayerImage>>()
    val teamsList = MutableLiveData<ArrayList<Team>>()

    val flow = Pager(PagingConfig(pageSize = 20)){
        PlayerPagingSource(Network().getNbaService())
    }.flow.cachedIn(viewModelScope)

    fun getTeamsList(){
        viewModelScope.launch {
            teamsList.value = Network().getNbaService().getTeams().data
        }
    }

    fun getPlayerImages(playerId: Int): ArrayList<PlayerImage> {
        return Network().getSofaService().getPlayerImages(playerId).data
    }
}