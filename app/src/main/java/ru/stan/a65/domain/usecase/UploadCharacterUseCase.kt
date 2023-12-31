package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.CharacterRepository

class UploadCharacterUseCase(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke(id: Int = 1) = repo.getCharacterByIdFromNetwork(id)
}