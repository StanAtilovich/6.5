package ru.stan.a65.data.paging.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.stan.a65.data.paging.api.RetrofitInstance
import ru.stan.a65.data.paging.mapper.CharacterPagingMapper
import ru.stan.a65.domain.model.CharacterPagingItem
import kotlin.math.max
import kotlin.math.min

class CharacterPagingSource : PagingSource<Int, CharacterPagingItem>() {
    private val api = RetrofitInstance
    private val mapper = CharacterPagingMapper()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterPagingItem> {
        val page = params.key ?: STARTING_KEY

       return kotlin.runCatching {
            mapper.mapDtoPagingToItemPaging(api.searchCharactersApi.getCharacters(page).data)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (page == LAST_PAGE) null else ensureValueKey(page + 1)
                )
            },
            onFailure = { LoadResult.Error(it) }
        )

    }

    override fun getRefreshKey(state: PagingState<Int, CharacterPagingItem>): Int {
        return STARTING_KEY
    }

    private fun ensureValueKey(key: Int) = min(max(STARTING_KEY, key), LAST_PAGE)

    companion object {
        private const val STARTING_KEY = 1
        private const val LAST_PAGE = 41
    }


}