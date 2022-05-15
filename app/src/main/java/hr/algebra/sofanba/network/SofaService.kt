package hr.algebra.sofanba.network

import hr.algebra.sofanba.network.model.response.PlayerImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SofaService {

    @GET("player-image/player/{playerId}")
    fun getPlayerImages(@Path("playerId")playerId: Int): PlayerImageResponse

}