package ru.stan.a65.di

import dagger.Component
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListViewModelFactory

@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class, ContextModule::class, BindImpls::class])
interface ApplicationComponent {
    fun listViewModelFactory(): ListViewModelFactory
}