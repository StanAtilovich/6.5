package ru.stan.a65.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.stan.a65.data.repository.CharacterRepositoryImpl.getCharacters
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.usecase.GetCharacterListUseCase


private const val TAG = "CharacterListViewModel55555"

class ListCharactersViewModel(
    private val getCharactersListUseCase: GetCharacterListUseCase
) : ViewModel() {

    private var _isLoading = MutableStateFlow<ProgressState>(ProgressState.Success)
    val state = _isLoading.asStateFlow()

    val onlySlizerin = MutableStateFlow<Boolean>(false)

    private var _characterList = MutableStateFlow<List<CharacterItem>>(mutableListOf())
    val characterList: StateFlow<List<CharacterItem>> =
        combine(_characterList, onlySlizerin) { characters, onlySlizerin ->
            if (onlySlizerin) {
                characters.filter {
                    it.hogwartsHouse == "Slytherin"
                }
            } else {
                characters
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _characterList.value
        )


    init {
        getCharacters()
    }


    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = ProgressState.Loading
                getCharactersListUseCase()
            }.fold(
                onSuccess = { _characterList.value = it },
                onFailure = { Log.e(TAG, "${it.message}", it) }
            )
            _isLoading.value = ProgressState.Success

        }
    }

    fun refresh() {
        getCharacters()
    }


}