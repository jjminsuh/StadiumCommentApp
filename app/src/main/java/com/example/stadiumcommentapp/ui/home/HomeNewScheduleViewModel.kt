package com.example.stadiumcommentapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stadiumcommentapp.data.model.DateInfo
import com.example.stadiumcommentapp.data.model.WatchType
import com.example.stadiumcommentapp.data.room.StadiumInfoRepository
import com.example.stadiumcommentapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeNewScheduleViewModel @Inject constructor(
    private val stadiumInfoRepository: StadiumInfoRepository
) : ViewModel() {

    private val _date = MutableLiveData<DateInfo>()
    val date: LiveData<DateInfo> = _date

    private val _teamArray = MutableLiveData<ArrayList<String>>()
    val teamArray: LiveData<ArrayList<String>> = _teamArray

    private val _homeTeam = MutableLiveData<String>()
    val homeTeam: LiveData<String> = _homeTeam

    private val _awayTeam = MutableLiveData<String>()
    val awayTeam: LiveData<String> = _awayTeam

    private val _stadiumName = MutableLiveData<String>()
    val stadiumName: LiveData<String> = _stadiumName

    private val _watchType = MutableLiveData<WatchType>()
    val watchType: LiveData<WatchType> = _watchType

    private val _eventSelectDate = MutableLiveData<Event<DateInfo>>()
    val eventSelectDate: LiveData<Event<DateInfo>> = _eventSelectDate

    private val _eventSelectTeamBottomSheet = MutableLiveData<Event<String>>()
    val eventSelectTeamBottomSheet: LiveData<Event<String>> = _eventSelectTeamBottomSheet

    private val _isHome = MutableLiveData<Boolean>()
    val isHome: LiveData<Boolean> = _isHome

    private val _eventSelectType = MutableLiveData<Event<WatchType>>()
    val eventSelectType: LiveData<Event<WatchType>> = _eventSelectType

    fun setDate(date: String) {
        val token = date.split("-")
        _date.value = DateInfo(token[0], token[1], token[2])
    }

    fun loadTeamList() {
        _teamArray.value = arrayListOf("DB", "삼성", "SK", "LG", "오리온", "KCC", "KGC", "KT", "한국가스공사", "현대모비스")
    }

    fun setIsHome(boolean: Boolean) {
        _isHome.value = boolean
    }

    fun setHomeTeam(homeTeam: String) {
        _homeTeam.value = homeTeam
    }

    fun setAwayTeam(awayTeam: String) {
        _awayTeam.value = awayTeam
    }

    fun setStadiumInfo(homeTeam: String) {
        stadiumInfoRepository.getHomeStadium(homeTeam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("GET_FROM_ROOM", "get stadium name success")
                _stadiumName.value = it
            }, {
                Log.d("GET_FROM_ROOM", "get stadium name fail: ${it.message}")
            })
    }

    fun onClickSelectDate(dateInfo: DateInfo) {
        _eventSelectDate.value = Event(dateInfo)
    }

    fun onClickTeamBottomSheet(team: String) {
        _eventSelectTeamBottomSheet.value = Event(team)
    }

    fun onClickType(type: WatchType) {
        if(watchType.value == type) {
            _eventSelectType.value = Event(WatchType.NULL)
            _watchType.value = WatchType.NULL
        } else {
            _eventSelectType.value = Event(type)
            _watchType.value = type
        }
    }
}