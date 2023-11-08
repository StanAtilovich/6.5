package ru.stan.a65.presentation.ui.FragmentWorkManager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a65.App


class WorkManagerViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkManagerViewModel::class.java)) {
            return WorkManagerViewModel(App.INSTANCE) as T
        }
        throw RuntimeException("Неизвестный класс")
    }
}