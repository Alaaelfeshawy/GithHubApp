package com.example.githubrepoapp.data.cache.mappers

import com.example.githubrepoapp.data.cache.models.IssuesCacheEntity
import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.cache.util.EntityMapper
import javax.inject.Inject

class IssueCacheMapper @Inject constructor(): EntityMapper<IssuesCacheEntity, IssueModel>
{

    fun entityListToIssueList(entities: List<IssuesCacheEntity>?): List<IssueModel>{
        val list: ArrayList<IssueModel> = ArrayList()
        if (entities != null) {
            for(entity in entities){
                list.add(mapFromEntity(entity))
            }
        }
        return list
    }

    fun issueListToEntityList(model: List<IssueModel>?): List<IssuesCacheEntity>{
        val entities: ArrayList<IssuesCacheEntity> = ArrayList()
        if (model != null) {
            for(item in model){
                entities.add(mapToEntity(item))
            }
        }
        return entities
    }

    override fun mapFromEntity(entity: IssuesCacheEntity?): IssueModel {
        return IssueModel(
            id = entity?.id,
            title = entity?.title,
            author = entity?.author,
            state = entity?.state,
            createdAt = entity?.createdAt
        )
    }

    override fun mapToEntity(model: IssueModel?): IssuesCacheEntity {
        return IssuesCacheEntity(
            author = model?.author,
            title = model?.title,
            id = model?.id,
            state = model?.state,
            createdAt = model?.createdAt
        )
    }
}







