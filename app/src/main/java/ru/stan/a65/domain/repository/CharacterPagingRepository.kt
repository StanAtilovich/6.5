package ru.stan.a65.domain.repository

import ru.stan.a65.domain.model.CharacterPagingItem

interface CharacterPagingRepository {
    suspend fun getCharacters(): List<CharacterPagingItem>
}