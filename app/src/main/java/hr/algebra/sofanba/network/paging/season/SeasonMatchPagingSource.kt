package hr.algebra.sofanba.network.paging.season

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hr.algebra.sofanba.network.NbaService
import hr.algebra.sofanba.network.model.Match
import java.lang.Exception

class SeasonMatchPagingSource(
    private val service: NbaService,
    private val season: Int,
    private val startDate: String,
    private val postseason: Boolean
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
            val response = service.getAllMatchesForSeason(season, startDate, postseason, 20, nextPageNumber)
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