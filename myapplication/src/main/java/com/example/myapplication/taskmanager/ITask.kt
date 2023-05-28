package com.example.myapplication.taskmanager

/**
@Author zhang tao
@Date   11/27/22 12:39 AM
@Desc
 */
interface ITask {
    fun onTaskExecuteSucceed()
    fun onTaskExecuteFailed(message:String)
}