package com.example.booksearch.di

import com.example.shoppingapp.data.repository.ShopSearchRepository
import com.example.shoppingapp.data.repository.ShopSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Binds를 적절하게 활용했군요
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindShopSearchRepository(
        shopSearchRepositoryImpl: ShopSearchRepositoryImpl,
    ): ShopSearchRepository
}
