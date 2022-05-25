package hr.algebra.sofanba.network.model

data class GameStats(
    val id: Int,
    val ast: Int,
    val blk: Int,
    val dreb: Int,
    val fg3_pct: Double,
    val fg3a: Int,
    val fg3m: Int,
    val fg_pct: Double,
    val fga: Int,
    val fgm: Int,
    val ft_pct: Double,
    val fta: Int,
    val ftm: Int,
    val game: Game,
    val min: String,
    val oreb: Int,
    val pf: Int,
    val player: Player,
    val pts: Int,
    val reb: Int,
    val stl: Int,
    val team: Team,
    val turnover: Int
) {
}

data class Game(
    val id: Int,
    val date: String,
    val home_team_id: Int,
    val home_team_score: Int,
    val period: Int,
    val postseason: Boolean,
    val status: String,
    val time: String,
    val visitor_team_id: Int,
    val visitor_team_score: Int
)