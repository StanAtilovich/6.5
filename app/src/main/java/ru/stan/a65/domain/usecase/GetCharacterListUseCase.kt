package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke(): List<CharacterItem> {
        return repo.getCharacters()
    }
}