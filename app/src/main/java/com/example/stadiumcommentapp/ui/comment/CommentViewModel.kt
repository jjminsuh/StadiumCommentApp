package com.example.stadiumcommentapp.ui.comment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stadiumcommentapp.data.room.StadiumInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val stadiumInfoRepository: StadiumInfoRepository
) : ViewModel() {

    private val _stadiumList = MutableLiveData<Array<String>>()
    val stadiumList: LiveData<Array<String>> = _stadiumList

    fun loadStadiumList() {
//        _stadiumList.value =
//            arrayOf(
//                "원주 DB 프로미",
//                "서울 삼성 썬더스",
//                "서울 SK 나이츠",
//                "창원 LG 세이커스",
//                "고양 오리온 오리온스",
//                "전주 KCC 이지스",
//                "안양 KGC 인삼공사",
//                "수원 KT 소닉붐",
//                "대구 한국가스공사 페가수스",
//                "울산 현대모비스 피버스"
//            )
        stadiumInfoRepository.getAllTeam()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("GET_FROM_ROOM", "get all team success")
                _stadiumList.value = it
            }, {
                Log.d("GET_FROM_ROOM", "get all team fail : ${it.message}")
            })
    }
}