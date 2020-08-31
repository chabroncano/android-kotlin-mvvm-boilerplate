package com.chabroncano.mvvm_boilerplate.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleTextViewViewModel: ViewModel() {
    var counterLiveData = MutableLiveData<String>()

    fun startTimer() {
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                counterLiveData.value = (millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {}
        }.start()
    }
}
