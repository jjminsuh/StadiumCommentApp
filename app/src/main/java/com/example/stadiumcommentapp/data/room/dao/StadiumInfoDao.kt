package com.example.stadiumcommentapp.data.room.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StadiumInfoDao {
    @Query("SELECT stadium_name FROM stadium_info")
    fun getAllStadium(): ArrayList<String>
}