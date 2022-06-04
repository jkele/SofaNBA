package hr.algebra.sofanba.network

import hr.algebra.sofanba.network.model.response.*
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
    suspend fun getAllMatchesForSeason(
        @Query("seasons[]") season: Int,
        @Query("start_date") startDate: String,
        @Query("postseason") postseason: Boolean,
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ) : MatchResponse

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

    @GET("stats")
    suspend fun getStatsForMatchPerPlayer(
        @Query("seasons[]") season: Int,
        @Query("player_ids[]") playerId: Int,
        @Query("postseason") postseason: Boolean,
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ) : StatsResponse

    @GET("stats")
    suspend fun getPlayerSeason(
        @Query("player_ids[]") playerId: Int,
        @Query("postseason") postseason: Boolean,
        @Query("page") pageNumber: Int
    ) : StatsResponse

    @GET("season_averages")
    suspend fun getSeasonAverages(
        @Query("season") season: Int,
        @Query("player_ids[]") playerId: Int
    ) : SeasonStatsResponse

    @GET("stats")
    suspend fun getStatsForMatch(
        @Query("game_ids[]") gameId: Int,
        @Query("per_page") numberOfResults: Int,
        @Query("page") pageNumber: Int
    ) : StatsResponse

}