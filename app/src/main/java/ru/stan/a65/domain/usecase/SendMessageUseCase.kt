package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.ForumRepository


class SendMessageUseCase(
    private val repo : ForumRepository
){
    operator fun invoke(forumItem: String){
        repo.sendMessage(forumItem)
    }
}