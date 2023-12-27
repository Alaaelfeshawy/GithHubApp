package com.example.githubrepoapp.data.cache.mappers

import com.example.githubrepoapp.data.cache.models.RepositoriesCacheEntity
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.cache.util.EntityMapper
import javax.inject.Inject

class RepositoriesCacheMapper @Inject constructor():
    EntityMapper<RepositoriesCacheEntity, RepositoryModel>
{

    fun entityListToRepositoriesList(entities: List<RepositoriesCacheEntity>?): List<RepositoryModel>{
        val list: ArrayList<RepositoryModel> = ArrayList()
        if (entities != null) {
            for(entity in entities){
                list.add(mapFromEntity(entity))
            }
        }
        return list
    }

    fun reposListToEntityList(repos: List<RepositoryModel>): List<RepositoriesCacheEntity>{
        val entities: ArrayList<RepositoriesCacheEntity> = ArrayList()
        for(repo in repos){
            entities.add(mapToEntity(repo))
        }
        return entities
    }

    override fun mapFromEntity(entity: RepositoriesCacheEntity?): RepositoryModel {
        return RepositoryModel(
            id = entity?.id,
            description = entity?.description,
            name = entity?.name,
            owner = entity?.owner?.let { OwnerCacheMapper().mapFromEntity(it) }
        )
    }

    override fun mapToEntity(model: RepositoryModel?): RepositoriesCacheEntity {
        return RepositoriesCacheEntity(
            id = model?.id,
            description = model?.description,
            name = model?.name,
            owner = model?.owner?.let { OwnerCacheMapper().mapToEntity(it) }
        )
    }
}







