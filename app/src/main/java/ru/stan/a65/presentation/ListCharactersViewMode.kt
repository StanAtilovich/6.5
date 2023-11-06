package ru.stan.a65.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.usecase.GetCharacterListUseCase


private const val TAG = "CharacterListViewModel55555"
class ListCharactersViewModel(
    private val getCharacterListUseCase : GetCharacterListUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<ProgressState>(ProgressState.Success)
    var state = _state.asStateFlow()



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
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                _characterList.value = getCharacterListUseCase()
            } catch (t: Throwable) {
                Log.e(TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }
    }
}