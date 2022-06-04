package hr.algebra.sofanba.network

import hr.algebra.sofanba.network.model.response.HighlightResponse
import hr.algebra.sofanba.network.model.response.PlayerImageResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface SofaService {

    @GET("player-image/player/{playerId}")
    suspend fun getPlayerImages(@Path("playerId")playerId: Int): PlayerImageResponse

    @GET("highlight/player/{playerId}")
    suspend fun getHighlightsForPlayer(@Path("playerId")playerId: Int): HighlightResponse

    @GET("highlight/event/{eventId}")
    suspend fun getHighlightsForMatch(@Path("eventId")matchId: Int): HighlightResponse

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("highlight")
    suspend fun addHighlightForMatch(
        @Body requestBody: RequestBody
    ) : Response<ResponseBody>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("player-image")
    suspend fun addImageForPlayer(
        @Body requestBody: RequestBody
    ) : Response<ResponseBody>

    @DELETE("highlight/{highlightId}")
    suspend fun deleteMatchHighlight(@Path("highlightId") highlightId: Int) : Response<ResponseBody>?

    @DELETE("player-image/{imageId}")
    suspend fun deletePlayerImage(@Path("imageId") imageId: Int) : Response<ResponseBody>?
}