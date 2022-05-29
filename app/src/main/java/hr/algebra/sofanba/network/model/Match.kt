package hr.algebra.sofanba.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match(
    val id: Int,
    val date: String,
    @SerializedName("home_team_score")
    val homeTeamScore: Int,
    @SerializedName("visitor_team_score")
    val visitorTeamScore: Int,
    val season: Int,
    val period: Int,
    val status: String,
    val time: String,
    val postseason: Boolean,
    @SerializedName("home_team")
    val homeTeam: Team,
    @SerializedName("visitor_team")
    val visitorTeam: Team
): Serializable {
}