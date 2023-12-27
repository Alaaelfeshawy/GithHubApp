package com.example.githubrepoapp.data.cache.mappers

import com.example.githubrepoapp.data.cache.models.OwnerCacheEntity
import com.example.githubrepoapp.data.network.models.OwnerModel
import com.example.githubrepoapp.data.cache.util.EntityMapper
import javax.inject.Inject

class OwnerCacheMapper @Inject constructor(): EntityMapper<OwnerCacheEntity, OwnerModel>
{

    override fun mapFromEntity(entity: OwnerCacheEntity?): OwnerModel {
        return OwnerModel(
            id = entity?.id,
            login = entity?.login,
            starred_url = entity?.starred_url,
            type = entity?.type,
        )
    }

    override fun mapToEntity(model: OwnerModel?): OwnerCacheEntity {
        return OwnerCacheEntity(
            id = model?.id,
            login = model?.login,
            starred_url = model?.starred_url,
            type = model?.type,
        )
    }
}







