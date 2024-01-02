package com.kirdevelopment.worship47compose.di

import com.kirdevelopment.worship47compose.common.Constants.BASE_URL
import com.kirdevelopment.worship47compose.data.remote.Worship47Api
import com.kirdevelopment.worship47compose.data.repository.Worship47RepositoryImp
import com.kirdevelopment.worship47compose.domain.repository.Worship47Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWorshipApi(): Worship47Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Worship47Api::class.java)
    }

    @Provides
    @Singleton
    fun provideWorshipRepository(api: Worship47Api): Worship47Repository {
        return Worship47RepositoryImp(api)
    }
}