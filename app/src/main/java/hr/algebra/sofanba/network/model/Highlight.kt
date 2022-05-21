package hr.algebra.sofanba.network.model

data class Highlight(
    val eventId: Int,
    val startTimestamp: Int,
    val name: String,
    val url: String,
    val playerIdList: ArrayList<Int>
) {
}