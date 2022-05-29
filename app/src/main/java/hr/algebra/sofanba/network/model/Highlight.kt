package hr.algebra.sofanba.network.model

data class Highlight(
    val eventId: Int?,
    val name: String?,
    val url: String?,
    val playerIdList: ArrayList<Int>?,
    val id: Int?,
    val startTimestamp: Int?
) {
}