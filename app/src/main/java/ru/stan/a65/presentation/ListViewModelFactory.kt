package ru.stan.a65.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.domain.usecase.GetCharacterListUseCase


class ListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCharactersViewModel::class.java)){
            return ListCharactersViewModel(GetCharacterListUseCase(CharacterRepositoryImpl)) as T
        }
        throw java.lang.IllegalAccessException("неизвестный класс 2")
    }
}