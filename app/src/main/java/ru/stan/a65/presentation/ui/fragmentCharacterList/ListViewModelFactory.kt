package ru.stan.a65.presentation.ui.fragmentCharacterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class ListViewModelFactory @Inject constructor(
    private val listCharactersViewModel: ListCharactersViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCharactersViewModel::class.java)) {
            return listCharactersViewModel as T
        }
        throw RuntimeException("неизвестный класс")
    }
}