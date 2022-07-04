package hr.algebra.sofanba.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import hr.algebra.sofanba.network.model.Team
import java.io.Serializable

@Entity(tableName = "splashTeams")
data class SplashTeam(
    @PrimaryKey
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val fullName: String,
    val name: String
) : Serializable {

    fun convertToTeam(): Team {
        return Team(
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