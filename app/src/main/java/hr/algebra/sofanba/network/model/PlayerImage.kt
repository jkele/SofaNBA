package hr.algebra.sofanba.network.model

import java.io.Serializable

data class PlayerImage(
    val playerId: Int,
    val imageUrl: String,
    val imageCaption: String,
    val id: Int?
): Serializable