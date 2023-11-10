package ru.stan.a65.presentation.ui.fragmentForum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ForumViewModelFactory (private val forumViewModel: ForumViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)){
            return forumViewModel as T
        }
        throw RuntimeException("НЕизвестное инмя класаа")
    }
}