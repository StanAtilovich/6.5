package ru.stan.a65.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.stan.a65.App
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.presentation.ui.fragmentCharacter.MainFragment
import ru.stan.a65.presentation.ui.fragmentCharacterList.ListCharacters
import ru.stan.a65.presentation.ui.fragmentForum.ForumFragment
import ru.stan.a65.presentation.ui.fragmentPaging.PagingFragment
import ru.stan.a65.presentation.ui.fragmentPaging.PagingViewModelFactory
import ru.stan.a65.presentation.ui.fragmentWorkManager.WorkManagerFragment
import ru.stan.a65.presentation.worker.CasherDataWorkerFactory
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataModule::class,
        //  DomainModule::class,
        // PresentationModule::class,
        //   ContextModule::class,
        BindImpls::class,
        FirebaseModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun inject(app: App)
    fun injectMainFragment(mainFragment: MainFragment)
    fun injectlistCharacters(listCharacters: ListCharacters)
    fun injectForumFragment(forumFragment: ForumFragment)
    fun injectWorkManagerFragment(workManagerFragment: WorkManagerFragment)
    fun injectPagingFragment(pagingFragment: PagingFragment)


    fun casherViewModelModelFactory(): CasherDataWorkerFactory



   // fun firebaseUtils(): FirebaseUtils


}