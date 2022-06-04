package hr.algebra.sofanba.network.model.response

import hr.algebra.sofanba.network.model.Meta
import hr.algebra.sofanba.network.model.Team

data class TeamResponse(
    val data: ArrayList<Team>,
    val meta: Meta
)