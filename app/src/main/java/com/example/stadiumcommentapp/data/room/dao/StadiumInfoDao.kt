package com.example.stadiumcommentapp.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface StadiumInfoDao {
    @Query("SELECT stadium_name FROM stadium_info")
    fun getAllStadium(): Single<Array<String>>

    @Query("SELECT stadium_name FROM stadium_info WHERE team_name_short = :homeTeamShort")
    fun getHomeStadium(homeTeamShort: String): Single<String>
}