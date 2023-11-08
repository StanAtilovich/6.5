package ru.stan.a65.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import ru.stan.a65.domain.model.CharacterPagingItem
import java.lang.Math.max

class CharacterPagingSource : PagingSource<Int, CharacterPagingItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterPagingItem> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)

        if (start != STARTING_KEY) delay(3_000)
        return LoadResult.Page(
            data = range.map { number ->
                CharacterPagingItem(
                    id = number.toString(),
                    name = "Name of character $number"
                )
            },
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValueKey(range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterPagingItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val character = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValueKey(character.id.toInt() - state.config.pageSize / 2)
    }

    private fun ensureValueKey(key: Int) = max(STARTING_KEY, key)

    companion object {
        private const val STARTING_KEY = 1
    }


}