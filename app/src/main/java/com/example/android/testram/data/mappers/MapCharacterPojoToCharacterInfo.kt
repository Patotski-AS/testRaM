package com.example.android.testram.data.mappers

import com.example.android.testram.data.network.pojo.CharacterPojo
import com.example.android.testram.domain.model.CharacterInfo
import com.example.android.testram.domain.utill.Mapper

class MapCharacterPojoToCharacterInfo : Mapper<CharacterPojo, CharacterInfo> {
    override fun map(from: CharacterPojo): CharacterInfo {
        return CharacterInfo(
            id = from.id,
            name = from.name,
            status = from.status,
            species = from.species,
            type = from.type,
            gender = from.gender,
            origin = from.origin.name,
            location = from.location.name,
            image = from.image,
            episode = from.episode,
            url = from.url
        )
    }
}
