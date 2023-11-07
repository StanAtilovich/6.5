package ru.stan.a65.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a65.App
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.GetCharacterListUseCase


class ListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCharactersViewModel::class.java)) {

            val repo = CharacterRepositoryImpl(App.INSTANCE)
            val getCharacterListUseCase = GetCharacterListUseCase(repo)
            return ListCharactersViewModel(
                getCharacterListUseCase
            ) as T
        }
        throw RuntimeException("неизвестный класс 2")
    }
}