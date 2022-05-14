package hr.algebra.sofanba.network.model

import com.google.gson.annotations.SerializedName
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
): Serializable {
}