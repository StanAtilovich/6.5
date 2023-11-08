package ru.stan.a65.domain.model

data class CharacterPagingItem(
    val id: String,
    val name: String?,
    val bloodStatus: String? = null,
    val hogwartsHouse: String? = null,
    val patronus: String? = null,
    val imageUrl: String? = null
)
