package hr.algebra.sofanba.network.model

import com.google.gson.annotations.SerializedName
import hr.algebra.sofanba.database.model.FavoriteTeam
import hr.algebra.sofanba.database.model.SplashTeam
import java.io.Serializable

data class Team(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    @SerializedName("full_name")
    val fullName: String,
    val name: String
) : Serializable {

    fun convertToFavoriteTeam(): FavoriteTeam {
        return FavoriteTeam(
            this.id,
            this.abbreviation,
            this.city,
            this.conference,
            this.division,
            this.fullName,
            this.name
        )
    }

    fun convertToSplashTeam(): SplashTeam {
        return SplashTeam(
            this.id,
            this.abbreviation,
            this.city,
            this.conference,
            this.division,
            this.fullName,
            this.name
        )
    }

}