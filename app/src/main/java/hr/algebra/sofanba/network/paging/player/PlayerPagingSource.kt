package hr.algebra.sofanba.network.paging.player

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hr.algebra.sofanba.network.NbaService
import hr.algebra.sofanba.network.model.Player
import java.lang.Exception

class PlayerPagingSource(val service: NbaService): PagingSource<Int, Player>() {

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.getPlayers(20, nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = response.meta.nextPage
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}