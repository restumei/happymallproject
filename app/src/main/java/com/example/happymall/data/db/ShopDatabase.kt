package com.example.happymall.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.happymall.data.model.CartItem2
import com.example.happymall.data.model.ShopItem

@Database(entities = [CartItem2::class, ShopItem::class], version = 8, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun shopDao(): ShopDAO

}