package hr.algebra.sofanba.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.Team
import java.io.Serializable

@Entity(tableName = "favoritePlayers")
data class FavoritePlayer(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val height_feet: Int?,
    val height_inches: Int?,
    val lastName: String,
    val position: String,
    val team: Team,
    val weightPounds: Int?
) : Serializable {

    fun convertToPlayer(): Player {
        return Player(
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