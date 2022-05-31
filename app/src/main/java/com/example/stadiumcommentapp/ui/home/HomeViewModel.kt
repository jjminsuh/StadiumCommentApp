package com.example.stadiumcommentapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stadiumcommentapp.data.StadiumInfo
import com.example.stadiumcommentapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _calendarList = MutableLiveData<ArrayList<String>>()
    val calendarList: LiveData<ArrayList<String>> = _calendarList

    private val _thisMonth = MutableLiveData<String>()
    val thisMonth: LiveData<String> = _thisMonth

    private val _thisYear = MutableLiveData<String>()
    val thisYear: LiveData<String> = _thisYear

    private val _interestStadium = MutableLiveData<StadiumInfo>()
    val interestStadium: LiveData<StadiumInfo> = _interestStadium

    private val _eventDateClick = MutableLiveData<Event<String>>()
    val eventDateClick: LiveData<Event<String>> = _eventDateClick

    fun loadInfo() {
        _interestStadium.value = StadiumInfo("수원 KT 소닉붐 아레나", "주소", "전화번호")
    }

    fun setCalendar() {
        val today = GregorianCalendar()
        val dayList: ArrayList<String> = ArrayList()

        try {
            _thisYear.value = today.get(Calendar.YEAR).toString()
            _thisMonth.value = (today.get(Calendar.MONTH) + 1).toString()

            val firstDayOfMonth =
                GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1, 0, 0, 0)

            val firstDay = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1
            val lastDay = firstDayOfMonth.getActualMaximum(Calendar.DATE) + 1

            for (i in 0 until firstDay) {
                dayList.add("EMPTY")
            }

            for (i in 1 until lastDay) {
                dayList.add(i.toString())
            }

            _calendarList.value = dayList
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Error", e.toString())
        }
    }

    fun onClickDate(date: String) {
        _eventDateClick.value = Event(date)
    }
}