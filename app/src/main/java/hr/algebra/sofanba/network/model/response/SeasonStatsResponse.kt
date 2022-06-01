package hr.algebra.sofanba.network.model.response

import hr.algebra.sofanba.network.model.SeasonStats

data class SeasonStatsResponse(
    val data: ArrayList<SeasonStats>?
) {
}