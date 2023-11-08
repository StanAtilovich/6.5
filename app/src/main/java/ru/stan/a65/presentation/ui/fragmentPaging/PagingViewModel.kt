package ru.stan.a65.presentation.ui.fragmentPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.stan.a65.data.CharacterPagingRepository
import ru.stan.a65.domain.model.CharacterPagingItem

class PagingViewModel : ViewModel() {
    val repo = CharacterPagingRepository()
    val items: Flow<PagingData<CharacterPagingItem>> = Pager(
        config = PagingConfig(
            ITEM_PER_PAGE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { repo.characterPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)


    companion object {
        private const val ITEM_PER_PAGE = 50
    }

}