package com.netatmo.ylu.gofoot.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.netatmo.ylu.gofoot.model.Player
import com.netatmo.ylu.gofoot.retrofit.RequestClient

class PlayerPagingSource(
    val teamId: Int,
    val season: Int
) : PagingSource<Int, Player>() {
    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val response =
            RequestClient.getPlayersByTeamId(id = teamId, season = season, paging = position)
        val nextKey = response.paging?.let {
            if (it.total == it.current) {
                null
            } else {
                it.current.plus(1).coerceAtMost(it.total)
            }
        }

        Log.e("LOAD", "position $position, loadsize ${params.loadSize}, nextKey $nextKey")
        return LoadResult.Page(
            data = response.response.map { it.player },
            prevKey = if (position == STARTING_PAGE_INDEX) null else position,
            nextKey = nextKey,
        )
    }
}