package ru.stan.a65.presentation.ui.fragmentCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a65.App
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.GetCharacterUseCase


class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val repo = CharacterRepositoryImpl(App.INSTANCE, CharacterMapper())

            val useCase3 = GetCharacterUseCase(repo)
            return MainViewModel(useCase3) as T
        }
        throw RuntimeException("неизвестное имя классса")
    }
}