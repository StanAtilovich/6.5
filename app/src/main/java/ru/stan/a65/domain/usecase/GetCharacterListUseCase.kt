package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.CharacterRepository
import javax.inject.Inject


class GetCharacterListUseCase @Inject constructor(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke() =
        repo.getCharactersFromDb()
}