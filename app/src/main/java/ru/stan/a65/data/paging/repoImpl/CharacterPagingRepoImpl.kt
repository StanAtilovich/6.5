package ru.stan.a65.data.paging.repoImpl

import ru.stan.a65.data.paging.api.RetrofitInstance
import ru.stan.a65.data.paging.mapper.CharacterPagingMapper
import ru.stan.a65.domain.model.CharacterPagingItem
import ru.stan.a65.domain.repository.CharacterPagingRepository

class CharacterPagingRepoImpl:CharacterPagingRepository {
    val mapper = CharacterPagingMapper()
    override suspend fun getCharacters(): List<CharacterPagingItem> {
        return mapper.mapDtoPagingToItemPaging(
            RetrofitInstance.searchCharactersApi.getCharacters().data
        )
    }
}