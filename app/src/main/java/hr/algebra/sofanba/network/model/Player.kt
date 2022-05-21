package hr.algebra.sofanba.network.model

import com.google.gson.annotations.SerializedName
import hr.algebra.sofanba.database.model.FavoritePlayer
import java.io.Serializable

data class Player(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    val height_feet: Int?,
    val height_inches: Int?,
    @SerializedName("last_name")
    val lastName: String,
    val position: String,
    val team: Team,
    @SerializedName("weight_pounds")
    val weightPounds: Int?
): Serializable {

    fun convertToFavoritePlayer(): FavoritePlayer {
        return FavoritePlayer(
            this.id,
            this.firstName,
            this.height_feet,
            this.height_inches,
            this.lastName,
            this.position,
            this.team,
            this.weightPounds
        )
    }

}