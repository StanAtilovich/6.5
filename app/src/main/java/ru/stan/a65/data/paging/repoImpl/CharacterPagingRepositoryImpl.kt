package ru.stan.a65.data.paging.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.stan.a65.data.paging.pagingSource.CharacterPagingSource
import ru.stan.a65.domain.repository.CharacterPagingRepository


class CharacterPagingRepositoryImpl : CharacterPagingRepository {

    override fun getPager() = Pager(
        config = PagingConfig(ITEM_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { CharacterPagingSource() }
    )
    companion object{
        private const val ITEM_PER_PAGE = 100
    }
}
