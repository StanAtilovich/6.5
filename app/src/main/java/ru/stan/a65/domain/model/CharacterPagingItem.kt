package ru.stan.a65.domain.model

data class CharacterPagingItem(
    val id: String,
    val name: String?,
    val bloodStatus: String?,
    val hogwartsHouse: String?,
    val patronus: String?,
    val imageUrl: String?
)
