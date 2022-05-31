package com.example.stadiumcommentapp.ui.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class WriteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is write Fragment"
    }
    val text: LiveData<String> = _text
}