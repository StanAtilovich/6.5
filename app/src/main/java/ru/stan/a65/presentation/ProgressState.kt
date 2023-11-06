package ru.stan.a65.presentation

sealed class ProgressState{
    object Loading: ProgressState()
    object Success: ProgressState()
}