package ru.stan.a65.data.repository

import ru.stan.a65.data.paging.pagingSource.CharacterPagingSource

class CharacterPagingRepository {
    fun characterPagingSource() = CharacterPagingSource()
}