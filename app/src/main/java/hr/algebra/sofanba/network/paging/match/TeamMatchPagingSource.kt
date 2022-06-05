package hr.algebra.sofanba.network.paging.match

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hr.algebra.sofanba.network.NbaService
import hr.algebra.sofanba.network.model.Match
import java.lang.Exception


class TeamMatchPagingSource(
    private val service: NbaService,
    private val teamId: Int,
    private val season: Int,
    val postSeason: Boolean
) : PagingSource<Int, Match>() {
    override fun getRefreshKey(state: PagingState<Int, Match>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response =
                service.getAllPostseasonMatchesForTeam(teamId, season, postSeason, 20, nextPageNumber)
            

            LoadResult.Page(
                data = response.data.sortedBy { it.date },
                prevKey = null,
                nextKey = response.meta.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
