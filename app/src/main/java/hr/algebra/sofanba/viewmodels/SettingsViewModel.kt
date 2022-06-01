package hr.algebra.sofanba.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import hr.algebra.sofanba.database.NbaDatabase
import hr.algebra.sofanba.database.NbaRepository

class SettingsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NbaRepository

    init {
        val nbaDao = NbaDatabase.getDatabase(application).nbaDao()
        repository = NbaRepository(nbaDao)
    }

    fun clearFavoritesList() {
        repository.deleteFavoritePlayers()
        repository.deleteFavoriteTeams()
    }

}