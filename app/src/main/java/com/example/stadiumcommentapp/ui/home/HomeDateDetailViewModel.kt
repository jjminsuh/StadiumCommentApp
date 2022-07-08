package com.example.stadiumcommentapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stadiumcommentapp.data.model.DateInfo
import com.example.stadiumcommentapp.data.model.ScheduleInfo
import com.example.stadiumcommentapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeDateDetailViewModel @Inject constructor() : ViewModel() {

    private val _date = MutableLiveData<DateInfo>()
    val date: LiveData<DateInfo> = _date

    private val _scheduleList = MutableLiveData<ArrayList<ScheduleInfo>>()
    val scheduleList: LiveData<ArrayList<ScheduleInfo>> = _scheduleList

    private val _eventAddSchedule = MutableLiveData<Event<String>>()
    val eventAddSchedule: LiveData<Event<String>> = _eventAddSchedule

    fun setDate(date: String) {
        val token = date.split("-")
        _date.value = DateInfo(token[0], token[1], token[2])
    }

    fun setList() {
        val list: ArrayList<ScheduleInfo> = ArrayList()

        list.add(ScheduleInfo("수원 KT vs. 울산 현대모비스", "19:00", "수원 KT 소닉붐 아레나", "직관", "메모를 테스트 해보자 내용이 길어지며 어떻게 나오는지 궁금하구나 더 길어야 하는 구나 이정도로 길면 될까"))
        list.add(ScheduleInfo("원주 DB vs. 서울 삼성", "19:00", "원주종합체육관", "집관", "메모를 테스트 해보자 내용이 길어지며 어떻게 나오는지 궁금하구나 더 길어야 하는 구나 이정도로 길면 될까"))

        _scheduleList.value = list
    }

    fun onClickAddScedule(date: String) {
        _eventAddSchedule.value = Event(date)
    }
}