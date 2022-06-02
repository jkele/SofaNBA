package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository
import hr.algebra.sofanba.network.Network
import hr.algebra.sofanba.network.model.Highlight
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.PlayerImage
import kotlinx.coroutines.launch
import java.lang.Exception

class PlayerDetailsViewModel(application: Application): AndroidViewModel(application) {

    val highlightsList = MutableLiveData<ArrayList<Highlight>>()
    val imagesList = MutableLiveData<ArrayList<PlayerImage>>()
    val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application).nbaDao()
        repository = NbaRepository(nbaDao)
    }

    fun isPlayerFavorite(id: Int) : Boolean {
        return repository.isPlayerFavorite(id)
    }

    fun insertFavoritePlayer(player: Player) {
        repository.insertFavoritePlayer(player.convertToFavoritePlayer())
    }

    fun deleteFavoritePlayer(player: Player) {
        repository.deleteFavoritePlayer(player.convertToFavoritePlayer())
    }

    fun getHighlightsForPlayer(id: Int) {
        viewModelScope.launch {
            try {
                highlightsList.value = Network().getSofaService().getHighlightsForPlayer(id).data
            } catch (e: Exception){
                highlightsList.value = arrayListOf()
            }
        }
    }



    fun getPlayerImages(id: Int) {
        viewModelScope.launch {
            try {
                imagesList.value = Network().getSofaService().getPlayerImages(id).data
            } catch (e: Exception) {

            }
        }
    }
}