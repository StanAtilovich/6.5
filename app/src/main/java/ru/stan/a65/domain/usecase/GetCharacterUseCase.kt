package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repo: CharacterRepository
) {
    suspend operator fun invoke(id: Int = 1) =
        repo.getCharacterByIdFromDb(id)
}