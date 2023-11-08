package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.repository.CharacterRepository


class CashCharacterListUseCase(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke(characters: List<CharacterItem>) =
        repo.saveCharacterListToDb(characters)
}