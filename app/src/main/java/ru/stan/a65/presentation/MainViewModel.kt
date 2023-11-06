package ru.stan.a65.presentation

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.domain.usecase.GetCharacterUseCase


class MainViewModel(
    private val repository: CharacterRepositoryImpl,
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {
    private var _state: MutableStateFlow<ProgressState> = MutableStateFlow(value = ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character: MutableStateFlow<CharacterItem> =
        MutableStateFlow(CharacterItem())
    var character = _character.asStateFlow()

    private var _characterList: MutableStateFlow<List<CharacterItem>> =
        MutableStateFlow(mutableListOf())
    var characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                _character.value = getCharacterUseCase()
                _characterList.value = getCharacterListUseCase()
            } catch (t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }

    }

    fun randomCharacter() {
        // _character.value = _characterList.value.random()
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                val listSize = _characterList.value.size
                _character.value = getCharacterUseCase((1..listSize).random())
            } catch (t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }

    }
}