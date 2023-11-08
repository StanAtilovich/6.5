package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.CharacterRepository

class UploadListUseCase(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke() = repo.getCharactersFromNetwork()


}
