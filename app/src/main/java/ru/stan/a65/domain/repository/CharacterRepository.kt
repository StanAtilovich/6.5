package ru.stan.a65.domain.repository

import ru.stan.a65.domain.model.CharacterItem

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterItem>
    suspend fun getCharacterById(id: Int): CharacterItem
}