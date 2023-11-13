package ru.stan.a65.data.paging.mapper

import ru.stan.a65.data.paging.dto.DataJson
import ru.stan.a65.domain.model.CharacterPagingItem
import javax.inject.Inject

class CharacterPagingMapper @Inject constructor(){
    fun mapDtoPagingToItemPaging(dtoList: List<DataJson>) =
        dtoList.map {
            CharacterPagingItem(
                it.id,
                it.attributes.name,
                it.attributes.bloodStatus,
                it.attributes.hogwartsHouse,
                it.attributes.patronus,
                it.attributes.imageUrl
            )
        }
}