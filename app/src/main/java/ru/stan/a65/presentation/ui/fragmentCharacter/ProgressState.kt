package ru.stan.a65.presentation.ui.fragmentCharacter

sealed class ProgressState{
    object Loading: ProgressState()
    object Success: ProgressState()
}