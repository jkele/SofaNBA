package hr.algebra.sofanba.network

import hr.algebra.sofanba.network.model.response.PlayerResponse
import hr.algebra.sofanba.network.model.response.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NbaService {

    @GET("players")
    suspend fun getPlayers(
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ): PlayerResponse

    @GET("teams")
    suspend fun getTeams(
    ): TeamResponse
}