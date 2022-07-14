package com.example.stadiumcommentapp.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stadium_info")
data class StadiumInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val stadiumId: Int,
    @ColumnInfo(name = "team_name")
    val teamName: String,
    @ColumnInfo(name = "team_name_short")
    val teamNAmeShort: String,
    @ColumnInfo(name = "stadium_name")
    val stadiumName: String,
    @ColumnInfo(name = "stadium_address")
    val stadiumAddress: String,
    @ColumnInfo(name = "stadium_phone")
    val stadiumPhone: String,
    @ColumnInfo(name = "team_instagram")
    val teamInstagram: String,
    @ColumnInfo(name = "team_youtube")
    val teamYoutube: String
)
