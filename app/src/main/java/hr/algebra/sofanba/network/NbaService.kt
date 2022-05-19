package hr.algebra.sofanba.network

import hr.algebra.sofanba.network.model.response.MatchResponse
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

    @GET("games")
    suspend fun getAllMatchesForTeam(
        @Query("team_ids[]") teamId: Int,
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ) : MatchResponse

    @GET("games")
    suspend fun getAllPostseasonMatchesForTeam(
        @Query("team_ids[]") teamId: Int,
        @Query("postseason") postseason: Boolean,
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ) : MatchResponse

    @GET
    suspend fun getMatchesForTeamPerSeason(
        @Query("seasons[]") season: Int,
        @Query("team_ids[]") teamId: Int,
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ) : MatchResponse
}