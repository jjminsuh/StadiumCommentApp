package com.example.stadiumcommentapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stadiumcommentapp.data.ReviewListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    private val _myReviewList = MutableLiveData<ArrayList<ReviewListItem>>()
    val myReviewList: LiveData<ArrayList<ReviewListItem>> = _myReviewList

    fun loadMyReview(){
        var test = ArrayList<ReviewListItem> ()

        for(i in 0 until 10){
            test.add(ReviewListItem("잘 보이는 편이예요.", "kt소닉붐화이팅", "2022-03-08", "수원 KT 소닉붐 아레나","D4", "2022-02-11"))
        }

        _myReviewList.value = test
    }
}