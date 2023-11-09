package ru.stan.a65.domain.repository

import androidx.paging.Pager
import ru.stan.a65.domain.model.CharacterPagingItem

interface CharacterPagingRepository {
    fun getPager(): Pager<Int, CharacterPagingItem>
}