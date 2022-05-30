package com.example.stadiumcommentapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {

    private val _calendarList = MutableLiveData<ArrayList<String>>()
    val calendarList: LiveData<ArrayList<String>> = _calendarList

    private val _interestStadium = MutableLiveData<String>()
    val interestStadium: LiveData<String> = _interestStadium

    fun loadInfo() {
        _interestStadium.value = "설정한 관심경기장 보여주기"
    }

    fun setCalendar() {
        val today = GregorianCalendar()
        val dayList: ArrayList<String> = ArrayList()

        try {
            val firstDayOfMonth = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1, 0, 0, 0)

            val firstDay = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1
            val lastDay = firstDayOfMonth.getActualMaximum(Calendar.DATE) + 1

            for(i in 0 until firstDay){
                dayList.add("EMPTY")
            }

            for(i in 1 until lastDay){
                dayList.add(i.toString())
            }

            _calendarList.value = dayList
        }
        catch (e: Exception){
            e.printStackTrace()
            Log.d("Error", e.toString())
        }
    }
}