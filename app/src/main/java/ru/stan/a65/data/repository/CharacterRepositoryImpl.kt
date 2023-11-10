package ru.stan.a65.data.repository

import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.network.RetrofitInstance
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val mapper: CharacterMapper,
    private val characterDao: CharacterDao
): CharacterRepository {
    //это у нас Network
    override suspend fun getCharactersFromNetwork(): List<CharacterItem> {



        return mapper.mapListDtoToListModel(
            RetrofitInstance.searchCharacterApi.getCharacters())
    }





    override suspend fun getCharacterByIdFromNetwork(id: Int): CharacterItem {
        return mapper.mapDtoToModel(
            RetrofitInstance.searchCharacterApi.getCgaractersById(id)
        )
    }

    override suspend fun saveCharacterToDb(characterItem: CharacterItem) {
        characterDao.insertCharacterItem(
            mapper.mapModelToDbModel(characterItem))
    }

    //Local Room DataBase хранилище



    override suspend fun saveCharacterListToDb(characterList: List<CharacterItem>) {
        characterDao.insertCharacterList(
            mapper.mapModelListToDbModelList(characterList)
        )
    }

    override suspend fun getCharactersFromDb(): List<CharacterItem> {
        return mapper.mapDbModelListToModelList(characterDao.getAllCharacters())
    }
    override suspend fun getCharacterByIdFromDb(id: Int): CharacterItem {
        return mapper.mapDbModelToModel(characterDao.getAllCharacterById(id))
    }
}

