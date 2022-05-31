package com.example.stadiumcommentapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stadiumcommentapp.data.DateInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeDateDetailViewModel @Inject constructor() : ViewModel() {

    private val _date = MutableLiveData<DateInfo>()
    val date: LiveData<DateInfo> = _date

    fun setDate(date: String) {
        val token = date.split("-")
        _date.value = DateInfo(token[0], token[1], token[2])
    }
}