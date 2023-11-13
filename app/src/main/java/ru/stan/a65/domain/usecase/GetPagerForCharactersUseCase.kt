package ru.stan.a65.domain.usecase

import ru.stan.a65.domain.repository.CharacterPagingRepository
import javax.inject.Inject

class GetPagerForCharactersUseCase @Inject constructor(
    private val repo: CharacterPagingRepository
) {
    operator fun invoke() = repo.getPager()

}