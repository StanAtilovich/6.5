package ru.stan.a65.presentation.ui.fragmentPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.stan.a65.data.paging.repoImpl.CharacterPagingRepoImpl
import ru.stan.a65.domain.model.CharacterPagingItem
import ru.stan.a65.domain.usecase.GetDataForPagingUseCase

class PagingViewModel : ViewModel() {
    private val useCase = GetDataForPagingUseCase(CharacterPagingRepoImpl())

    private var _characters =
        MutableStateFlow<List<CharacterPagingItem>>(emptyList())
    val characters = _characters.asStateFlow()

    init {
        viewModelScope.launch {
            _characters.value = useCase().filter {
                it.name != null && it.imageUrl != null
            }
        }
    }

}