package ru.stan.a65.data.mapper

import ru.stan.a65.data.dto.CharacterDto
import ru.stan.a65.data.local.entity.CharacterDbModel
import ru.stan.a65.domain.model.CharacterItem

class CharacterMapper {
    fun mapDtoToModel(characterDto: CharacterDto) = CharacterItem(
        id = characterDto.id,
        name = characterDto.name,
        hogwartsHouse = characterDto.hogwartsHouse,
        imageUrl = characterDto.imageUrl
    )

    fun mapListDtoToListModel(
        dtoList: List<CharacterDto>
    ) = dtoList.map {
        mapDtoToModel(it)
    }

    fun mapModelToDbModel(characterItem: CharacterItem) = CharacterDbModel(
        id = characterItem.id,
        name = characterItem.name,
        hogwartsHouse = characterItem.hogwartsHouse,
        imageUrl = characterItem.imageUrl
    )

    fun mapModelListToDbModelList(characterList: List<CharacterItem>) = characterList.map {
        mapModelToDbModel(it)
    }

    fun mapDbModelToModel(characterDb: CharacterDbModel) = CharacterItem(
        id = characterDb.id,
        name = characterDb.name,
        hogwartsHouse = characterDb.hogwartsHouse,
        imageUrl = characterDb.imageUrl
    )

    fun mapDbModelListToModelList(characterDbModel: List<CharacterDbModel>) = characterDbModel.map {
        mapDbModelToModel(it)
    }
}