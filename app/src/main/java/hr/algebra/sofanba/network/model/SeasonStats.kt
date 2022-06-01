package hr.algebra.sofanba.network.model

data class SeasonStats(
    val games_played: Int,
    val player_id: Int,
    val season: Int,
    val min: String?,
    val fgm: Double,
    val fga: Double,
    val fg3m: Double,
    val fg3a: Double,
    val ftm: Double,
    val fta: Double,
    val oreb: Double,
    val dreb: Double,
    val reb: Double,
    val ast: Double,
    val stl: Double,
    val blk: Double,
    val turnover: Double,
    val pf: Double,
    val pts: Double,
    val fg_pct: Double,
    val fg3_pct: Double,
    val ft_pct: Double
) {

    fun convertMinToDouble(): Double {
        val min = this.min!!.replace(':', '.')
        return min.toDouble()
    }
}