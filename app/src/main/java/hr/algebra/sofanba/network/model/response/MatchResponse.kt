package hr.algebra.sofanba.network.model.response

import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.network.model.Meta

data class MatchResponse(
    val data: ArrayList<Match>,
    val meta: Meta
)