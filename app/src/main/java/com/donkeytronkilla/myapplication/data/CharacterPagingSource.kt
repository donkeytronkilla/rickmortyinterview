package com.donkeytronkilla.myapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.donkeytronkilla.myapplication.api.RickMortyApi
import com.donkeytronkilla.myapplication.model.Character
import java.lang.Exception

class CharacterPagingSource(private val api: RickMortyApi): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1

        return try {
            val result = api.getCharacters()
            LoadResult.Page(
                data = result.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= result.info.pages) null else page + (params.loadSize / RickMortyApi.PAGE_SIZE)
            )
        } catch (err: Exception) {
            LoadResult.Error(err)
        }
    }
}