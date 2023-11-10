package ru.stan.a65.di

import dagger.Component
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.presentation.ui.fragmentCharacter.MainViewModelFactory
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListViewModelFactory
import ru.stan.a65.presentation.ui.fragmentForum.ForumViewModelFactory

@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class, ContextModule::class, BindImpls::class, FirebaseModule::class])
interface ApplicationComponent {
    fun listViewModelFactory(): ListViewModelFactory
    fun mainViewModelFactory(): MainViewModelFactory
    fun forumViewModelModelFactory(): ForumViewModelFactory

    fun firebaseUtils(): FirebaseUtils
}