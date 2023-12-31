package ru.stan.a65.presentation.ui.fragmentWorkManager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class WorkManagerViewModelFactory @Inject constructor(
    private val workManagerViewModel: WorkManagerViewModel
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkManagerViewModel::class.java)) {
            return workManagerViewModel as T
        }
        throw RuntimeException("Неизвестный класс")
    }
}