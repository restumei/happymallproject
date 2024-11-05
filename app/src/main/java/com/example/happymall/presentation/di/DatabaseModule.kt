package com.example.happymall.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.happymall.data.db.ShopDAO
import com.example.happymall.data.db.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesShopDatabase(app: Application): ShopDatabase {
        return Room.databaseBuilder(app, ShopDatabase::class.java, "shop_database")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun providesShopDao(database: ShopDatabase): ShopDAO {
        return database.shopDao()
    }

}