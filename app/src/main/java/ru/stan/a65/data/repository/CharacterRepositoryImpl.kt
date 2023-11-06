package ru.stan.a65.data.repository

import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.network.RetrofitInstance
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.repository.CharacterRepository

object CharacterRepositoryImpl: CharacterRepository {
    private val mapper = CharacterMapper()

    override suspend fun getCharacters(): List<CharacterItem> {
        return mapper.mapListDtoToListModel(
            RetrofitInstance.searchCharacterApi.getCharacters())
    }

    override suspend fun getCharacterById(id: Int): CharacterItem {
        return mapper.mapDtoToModel(
            RetrofitInstance.searchCharacterApi.getCgaractersById(id)
        )
    }

}