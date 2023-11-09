package ru.stan.a65.presentation.ui.fragmentForum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a65.App
import ru.stan.a65.data.repository.ForumRepositoryImpl
import ru.stan.a65.domain.usecase.SendMessageUseCase


class ForumViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)){
            return ForumViewModel(SendMessageUseCase(ForumRepositoryImpl(App.INSTANCE))) as T
        }
        throw RuntimeException("НЕизвестное инмя класаа")
    }
}