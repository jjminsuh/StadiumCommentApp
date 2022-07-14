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

    private val _stadiumArray = MutableLiveData<Array<String>>()
    val stadiumArray: LiveData<Array<String>> = _stadiumArray

    fun loadStadiumList() {
        stadiumInfoRepository.getAllTeam()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("GET_FROM_ROOM", "get all team success")
                _stadiumArray.value = it
            }, {
                Log.d("GET_FROM_ROOM", "get all team fail : ${it.message}")
            })
    }
}