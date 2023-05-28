package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

/**
@Author zhang tao
@Date   5/28/23 4:40 PM
@Desc
 */
class MainViewModel : ViewModel() {
    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        Log.d(TAG, "init: ")
    }
}