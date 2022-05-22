package hr.algebra.sofanba.network.paging.playerMatch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hr.algebra.sofanba.network.NbaService
import hr.algebra.sofanba.network.model.GameStats
import java.lang.Exception

class PlayerMatchPagingSource(
    val service: NbaService,
    val season: Int,
    val playerId: Int,
    val postseason: Boolean
) : PagingSource<Int, GameStats>() {
    override fun getRefreshKey(state: PagingState<Int, GameStats>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameStats> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response =
                service.getStatsForMatchPerPlayer(season, playerId, postseason, 20, nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = response.meta.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}