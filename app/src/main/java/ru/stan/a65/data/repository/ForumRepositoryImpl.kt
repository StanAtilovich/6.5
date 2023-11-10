package ru.stan.a65.data.repository

import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.domain.model.ForumItem
import ru.stan.a65.domain.repository.ForumRepository


class ForumRepositoryImpl(
    private val firebaseInstance :FirebaseUtils
) : ForumRepository {

    override fun sendMessage(text: String) {
        val currentUser = firebaseInstance.authUtils.getUserName()
        val message = ForumItem(text, currentUser)
        firebaseInstance.forumReference.push().setValue(
            message
        )
    }
}