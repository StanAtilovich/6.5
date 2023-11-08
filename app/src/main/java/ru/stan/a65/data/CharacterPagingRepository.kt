package ru.stan.a65.data

import ru.stan.a65.data.paging.CharacterPagingSource

class CharacterPagingRepository {
    fun characterPagingSource() = CharacterPagingSource()
}