package ru.stan.a65.presentation.ui.fragmentPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.stan.a65.data.paging.repoImpl.CharacterPagingRepositoryImpl
import ru.stan.a65.domain.model.CharacterPagingItem
import ru.stan.a65.domain.usecase.GetPagerForCharactersUseCase

class PagingViewModel(
    private val useCase : GetPagerForCharactersUseCase
) : ViewModel() {

    val items: Flow<PagingData<CharacterPagingItem>> = useCase()
        .flow
        .cachedIn(viewModelScope)
}