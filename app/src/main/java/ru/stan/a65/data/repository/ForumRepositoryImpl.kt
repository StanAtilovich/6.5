package ru.stan.a65.data.repository

import android.app.Application
import ru.stan.a65.App
import ru.stan.a65.domain.model.ForumItem
import ru.stan.a65.domain.repository.ForumRepository



class ForumRepositoryImpl(
    application: Application
) : ForumRepository {
    val firebaseInstance = (application as App).firebaseInstance
    override fun sendMessage(text: String) {
        val currentUser = firebaseInstance.authUtils.getUserName()
        val message = ForumItem(text, currentUser)
        firebaseInstance.forumReference.push().setValue(
            message
        )
    }
}