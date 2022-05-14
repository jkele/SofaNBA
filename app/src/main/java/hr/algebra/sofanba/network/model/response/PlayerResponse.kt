package hr.algebra.sofanba.network.model.response

import hr.algebra.sofanba.network.model.Meta
import hr.algebra.sofanba.network.model.Player

data class PlayerResponse(
    val data: ArrayList<Player>,
    val meta: Meta
) {
}