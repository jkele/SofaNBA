package hr.algebra.sofanba.network.model.response

import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.Meta

data class StatsResponse(
    val data: ArrayList<GameStats>,
    val meta: Meta
) {
}