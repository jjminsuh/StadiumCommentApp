package com.example.stadiumcommentapp.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface StadiumInfoDao {
    @Query("SELECT team_name FROM stadium_info")
    fun getAllTeam(): Single<Array<String>>
}