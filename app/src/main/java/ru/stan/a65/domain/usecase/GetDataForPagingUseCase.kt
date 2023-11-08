package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.model.CharacterPagingItem
import ru.stan.a65.domain.repository.CharacterPagingRepository

class GetDataForPagingUseCase(
    private val repo: CharacterPagingRepository
) {
    suspend operator fun invoke(): List<CharacterPagingItem> {
        return repo.getCharacters()
    }
}