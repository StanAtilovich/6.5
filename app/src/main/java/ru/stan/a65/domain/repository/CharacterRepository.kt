package ru.stan.a65.domain.repository

import ru.stan.a65.domain.model.CharacterItem

interface CharacterRepository {
    //Network
    suspend fun getCharactersFromNetwork(): List<CharacterItem>
    suspend fun getCharacterByIdFromNetwork(id: Int): CharacterItem

    //Local
    suspend fun saveCharacterToDb(characterItem:CharacterItem)
    suspend fun saveCharacterListToDb(characterList: List<CharacterItem>)

    //тут получаем из local
    suspend fun getCharacterByIdFromDb(id: Int): CharacterItem
    suspend fun getCharactersFromDb():List<CharacterItem>
}