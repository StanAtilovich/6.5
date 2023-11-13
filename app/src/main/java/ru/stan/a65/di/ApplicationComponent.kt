package ru.stan.a65.di

import dagger.Component
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.presentation.ui.fragmentCharacter.MainViewModelFactory
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListViewModelFactory
import ru.stan.a65.presentation.ui.fragmentForum.ForumViewModelFactory
import ru.stan.a65.presentation.ui.fragmentPaging.PagingViewModelFactory
import ru.stan.a65.presentation.ui.fragmentWorkManager.WorkManagerViewModelFactory
import ru.stan.a65.presentation.worker.CasherDataWorkerFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class, ContextModule::class, BindImpls::class, FirebaseModule::class])
interface ApplicationComponent {
    fun listViewModelFactory(): ListViewModelFactory
    fun mainViewModelFactory(): MainViewModelFactory
    fun forumViewModelModelFactory(): ForumViewModelFactory

    fun firebaseUtils(): FirebaseUtils

    fun casherViewModelModelFactory(): CasherDataWorkerFactory

    fun pagingViewModelModelFactory(): PagingViewModelFactory
    fun workManagerViewModelModelFactory(): WorkManagerViewModelFactory
}