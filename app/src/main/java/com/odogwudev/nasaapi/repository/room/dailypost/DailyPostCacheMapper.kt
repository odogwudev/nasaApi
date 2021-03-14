package com.odogwudev.nasaapi.repository.room.dailypost

import com.odogwudev.nasaapi.model.DailyPost
import com.odogwudev.nasaapi.utils.EntityMapper
import javax.inject.Inject

class DailyPostCacheMapper @Inject constructor() : EntityMapper<DailyPostCacheEntity, DailyPost> {
    override fun mapFromEntity(entity: DailyPostCacheEntity): DailyPost {
        return DailyPost(
            entity.date,
            entity.explanation,
            entity.hdUrl,
            entity.title
        )
    }

    override fun mapToEntity(domainModel: DailyPost): DailyPostCacheEntity {
        return DailyPostCacheEntity(
            domainModel.date,
            domainModel.explanation,
            domainModel.hdUrl,
            domainModel.title
        )
    }

    fun mapFromEntityList(entities: List<DailyPostCacheEntity>): List<DailyPost> {
        return entities.map { mapFromEntity(it) }
    }
}