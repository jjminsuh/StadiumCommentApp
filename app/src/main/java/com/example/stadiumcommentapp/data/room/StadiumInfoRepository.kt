package com.example.stadiumcommentapp.data.room

import com.example.stadiumcommentapp.data.room.dao.StadiumInfoDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StadiumInfoRepository @Inject constructor(
    private val stadiumInfoDao: StadiumInfoDao
) {
    fun getAllTeam(): Single<Array<String>> {
        return stadiumInfoDao.getAllTeam()
    }

    fun getHomeStadium(homeTeamShort: String): Single<String> {
        return stadiumInfoDao.getHomeStadium(homeTeamShort)
    }
}