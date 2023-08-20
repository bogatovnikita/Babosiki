package com.bogatovnikita.babosiki.di

import com.bogatovnikita.babosiki.data.repository.PopularRepositoryImplementation
import com.bogatovnikita.babosiki.domain.repositoryies.PopularRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModuleDependencies {

    @Binds
    fun bindsPopularRepositoryToPopularRepositoryImplementation(popularRepository: PopularRepositoryImplementation): PopularRepository
}