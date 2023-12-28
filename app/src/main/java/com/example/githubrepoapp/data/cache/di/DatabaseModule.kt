package com.example.githubrepoapp.data.cache.di

import android.content.Context
import androidx.room.Room
import com.example.githubrepoapp.constants.Constant.DataConstant.DATABASE_NAME
import com.example.githubrepoapp.data.cache.database.AppDatabase
import com.example.githubrepoapp.data.cache.database.dao.DetailsDao
import com.example.githubrepoapp.data.cache.database.dao.IssuesDao
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDetailDao(appDatabase: AppDatabase): DetailsDao {
        return appDatabase.detailsDao()
    }
    @Provides
    fun provideRepositoriesDao(appDatabase: AppDatabase): RepositoryDao {
        return appDatabase.repoDao()
    }
    @Provides
    fun provideIssuesDao(appDatabase: AppDatabase): IssuesDao {
        return appDatabase.issuesDao()
    }
}