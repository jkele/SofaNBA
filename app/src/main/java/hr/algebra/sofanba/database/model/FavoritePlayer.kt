package hr.algebra.sofanba.database.model

import androidx.room.Entity
import hr.algebra.sofanba.network.model.Team
import java.io.Serializable

@Entity(tableName = "favoritePlayers")
data class FavoritePlayer(
    val id: Int,
    val firstName: String,
    val height_feet: Int?,
    val height_inches: Int?,
    val lastName: String,
    val position: String,
    val team: Team,
    val weightPounds: Int?
): Serializable {
}