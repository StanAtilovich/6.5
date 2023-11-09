package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.CharacterPagingRepository

class GetPagerForCharactersUseCase(
    private val repo: CharacterPagingRepository
) {
    operator fun invoke() = repo.getPager()

}