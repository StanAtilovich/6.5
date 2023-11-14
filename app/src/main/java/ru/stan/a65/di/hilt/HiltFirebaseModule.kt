package ru.stan.a65.di.hilt

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.stan.a65.data.firebase.AuthUtils
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.data.repository.ForumRepositoryImpl
import ru.stan.a65.domain.repository.ForumRepository
import ru.stan.a65.domain.usecase.SendMessageUseCase
import ru.stan.a65.presentation.ui.fragmentForum.ForumViewModel
import ru.stan.a65.presentation.ui.fragmentForum.ForumViewModelFactory

@Module
@InstallIn(SingletonComponent::class)
class HiltFirebaseModule {
    @Provides
    fun provideFirebaseDb(): FirebaseDatabase {
        return Firebase.database
    }

    @Provides
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics {
        return Firebase.crashlytics
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun provideAuthUI(): AuthUI {
        return AuthUI.getInstance()
    }

    @Provides
    fun provideAuthUtils(
        firebaseAuth: FirebaseAuth,
        authUI: AuthUI
    ): AuthUtils {
        return AuthUtils(
            firebaseAuth,
            authUI
        )
    }

    @Provides
    fun provideFirebaseUtils(
        firebaseDatabase: FirebaseDatabase,
        crashlytics: FirebaseCrashlytics,
        authUtils: AuthUtils
    ): FirebaseUtils {
        return FirebaseUtils(
            firebaseDatabase,
            crashlytics,
            authUtils
        )
    }

    @Provides
    fun provideForumRepositoryImpl(
        firebaseUtils: FirebaseUtils
    ): ForumRepositoryImpl {
        return ForumRepositoryImpl( firebaseUtils)
    }

    @Provides
    fun provideSendMessageUseCase(
        forumRepository: ForumRepository
    ): SendMessageUseCase {
        return SendMessageUseCase(forumRepository)
    }

    @Provides
    fun provideForumViewModel(
        sendMessageUseCase: SendMessageUseCase
    ): ForumViewModel {
        return ForumViewModel(sendMessageUseCase)
    }

    @Provides
    fun provideForumModelFactory(
        forumViewModel: ForumViewModel
    ): ForumViewModelFactory {
        return ForumViewModelFactory(forumViewModel)
    }
}