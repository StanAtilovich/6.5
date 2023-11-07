package ru.stan.a65.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.GetCharacterListUseCase
import ru.stan.a65.domain.usecase.GetCharacterUseCase


class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            val repo = CharacterRepositoryImpl
            val useCase = GetCharacterUseCase(repo)
            val useCase2 = GetCharacterListUseCase(repo)
            return MainViewModel(useCase2,useCase) as T
        }
        throw IllegalArgumentException("неизвестное имя классса")
    }
}