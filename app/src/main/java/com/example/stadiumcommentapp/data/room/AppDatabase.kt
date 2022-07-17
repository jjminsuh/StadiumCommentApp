package com.example.stadiumcommentapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stadiumcommentapp.data.room.dao.StadiumInfoDao
import com.example.stadiumcommentapp.data.room.entity.StadiumInfoEntity

@Database(entities = [StadiumInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stadiumInfoDao(): StadiumInfoDao
}
