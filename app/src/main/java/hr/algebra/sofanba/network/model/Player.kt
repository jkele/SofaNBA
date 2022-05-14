package hr.algebra.sofanba.network.model

import com.google.gson.annotations.SerializedName
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
    val weightPounds: Int?
): Serializable {
}