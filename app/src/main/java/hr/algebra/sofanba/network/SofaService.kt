package hr.algebra.sofanba.network

import hr.algebra.sofanba.network.model.response.HighlightResponse
import hr.algebra.sofanba.network.model.response.PlayerImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SofaService {

    @GET("player-image/player/{playerId}")
    fun getPlayerImages(@Path("playerId")playerId: Int): PlayerImageResponse

    @GET("highlight/player/{playerId}")
    suspend fun getHighlightsForPlayer(@Path("playerId")playerId: Int): HighlightResponse

    @GET("highlight/event/{eventId}")
    suspend fun getHighlightsForMatch(@Path("eventId")matchId: Int): HighlightResponse
}