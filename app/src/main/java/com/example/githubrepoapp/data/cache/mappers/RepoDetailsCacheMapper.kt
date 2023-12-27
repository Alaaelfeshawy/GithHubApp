package com.example.githubrepoapp.data.cache.mappers

import com.example.githubrepoapp.data.cache.models.RepoDetailCacheEntity
import com.example.githubrepoapp.data.network.models.OwnerModel
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.cache.util.EntityMapper
import javax.inject.Inject

class RepoDetailsCacheMapper @Inject constructor():
    EntityMapper<RepoDetailCacheEntity, RepositoryDetailsModel>
{

    override fun mapFromEntity(entity: RepoDetailCacheEntity?): RepositoryDetailsModel {
        return RepositoryDetailsModel(
            description = entity?.description,
            forks_count=entity?.forks_count,
            full_name=entity?.full_name,
            id=entity?.id,
            name=entity?.name,
            open_issues= entity?.open_issues,
             open_issues_count=entity?.open_issues_count,
             owner = OwnerCacheMapper().mapFromEntity(entity?.owner),
             pushed_at = entity?.pushed_at,
             stargazers_count = entity?.stargazers_count,
             updated_at= entity?.updated_at,
             watchers = entity?.watchers,
             watchers_count = entity?.watchers_count ,
        )
    }

    override fun mapToEntity(model: RepositoryDetailsModel?): RepoDetailCacheEntity {
        return RepoDetailCacheEntity(
            description = model?.description,
            forks_count=model?.forks_count,
            full_name=model?.full_name,
            id=model?.id,
            name=model?.name,
            open_issues= model?.open_issues,
            open_issues_count=model?.open_issues_count,
            owner = OwnerCacheMapper().mapToEntity(model?.owner),
            pushed_at = model?.pushed_at,
            stargazers_count = model?.stargazers_count,
            updated_at= model?.updated_at,
            watchers = model?.watchers,
            watchers_count = model?.watchers_count ,
        )
    }
}







