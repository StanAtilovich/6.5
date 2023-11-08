package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.repository.CharacterRepository

class CashCharacterUseCase(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke(characterItem: CharacterItem)=
        repo.saveCharacterToDb(characterItem)
}