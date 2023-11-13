package ru.stan.a65.presentation.ui.fragmentPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PagingViewModelFactory(
    private val pagingViewModel: PagingViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagingViewModel::class.java)) {
            return pagingViewModel as T
        }
        throw IllegalAccessException("неизвестное имя класса modelClass")
    }

}