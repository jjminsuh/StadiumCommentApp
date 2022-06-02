package com.example.stadiumcommentapp.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "stadium_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('원주 DB 프로미', '원주 종합 체육관', '강원도 원주시 서원대로 279', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('서울 삼성 썬더스', '잠실 실내체육관', '서울특별시 송파구 올림픽로 29 (잠실1동 10번지)', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('서울 SK 나이츠', '잠실 학생 체육관', '서울시 송파구 올림픽로25', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('창원 LG 세이커스', '창원체육관', '경남 창원시 성산구 원이대로 450 (창원체육관)', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('고양 오리온 오리온스', '고양 체육관', '경기도 고양시 일산서구 중앙로 1601 고양체육관 (대화동 2320)', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('전주 KCC 이지스', '전주실내체육관', '전북 전주시 덕진구 권삼득로 308', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('안양 KGC 인삼공사', '안양체육관', '안양시 동안구 비산3동 1023번지 안양체육관', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('수원 KT 소닉붐', '수원 KT 소닉붐 아레나', '경기도 수원시 권선구 서수원로577번길 171 수원 KT 소닉붐 아레나', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('대구 한국가스공사 페가수스', '대구체육관', '대구 북구 대구체육관로 39', '000-0000-0000')")
                    db.execSQL("insert into stadium_info (team_name, stadium_name, stadium_address, stadium_phone) values ('울산 현대모비스 피버스', '울산 동천체육관', '울산광역시 중구 염포로 85 (남외동)', '000-0000-0000')")
                }
            })
            .build()

    @Provides
    fun provideStadiumInfoDao(appDatabase: AppDatabase) = appDatabase.stadiumInfoDao()
}