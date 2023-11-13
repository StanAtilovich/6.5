package ru.stan.a65.presentation.ui.fragmentCharacter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.usecase.GetCharacterUseCase
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {
    private var _state: MutableStateFlow<ProgressState> = MutableStateFlow(value = ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character: MutableStateFlow<CharacterItem> =
        MutableStateFlow(CharacterItem())
    var character = _character.asStateFlow()

    private var _characterList: MutableStateFlow<List<CharacterItem>> =
        MutableStateFlow(mutableListOf())

    init {
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                _character.value =getCharacterUseCase()
            } catch (t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }

    }

    fun randomCharacter() {
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                val randomId = (1..23).random()
                _character.value = getCharacterUseCase(randomId)
            } catch (t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }

    }
}