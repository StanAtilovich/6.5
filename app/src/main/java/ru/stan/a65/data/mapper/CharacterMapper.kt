package ru.stan.a65.data.mapper

import ru.stan.a65.data.dto.CharacterDto
import ru.stan.a65.domain.model.CharacterItem

class CharacterMapper {
    fun mapDtoToModel(characterDto: CharacterDto)= CharacterItem(
        id = characterDto.id,
        name = characterDto.name,
        hogwartsHouse = characterDto.hogwartsHouse,
        imageUrl = characterDto.imageUrl
    )

    fun mapListDtoToListModel(dtoList:List<CharacterDto>): List<CharacterItem>{
        var resList = mutableListOf<CharacterItem>()
        dtoList.forEach { resList.add(mapDtoToModel(it)) }
        return resList
    }
}