package com.example.stadiumcommentapp.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor() : ViewModel() {

    private val _stadiumList = MutableLiveData<Array<String>>()
    val stadiumList: LiveData<Array<String>> = _stadiumList

    fun loadStadiumList() {
        _stadiumList.value =
            arrayOf(
                "원주 DB 프로미",
                "서울 삼성 썬더스",
                "서울 SK 나이츠",
                "창원 LG 세이커스",
                "고양 오리온 오리온스",
                "전주 KCC 이지스",
                "안양 KGC 인삼공사",
                "수원 KT 소닉붐",
                "대구 한국가스공사 페가수스",
                "울산 현대모비스 피버스"
            )
    }
}