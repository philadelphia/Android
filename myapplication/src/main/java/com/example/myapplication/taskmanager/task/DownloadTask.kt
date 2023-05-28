package com.example.myapplication.taskmanager.task

import android.util.Log
import com.example.myapplication.taskmanager.BaseTask
import com.example.myapplication.taskmanager.TaskStatus

/**
@Author zhang tao
@Date   11/27/22 12:55 AM
@Desc
 */
class DownloadTask(url: String) : BaseTask<Boolean>(url) {
    companion object {
        private const val TAG = "DownloadTask"
    }

    private fun doTask(): Boolean {
        Log.d(TAG, "downloadTask:downing..... url is $url ")
        Thread.sleep(3000)
        Log.d(TAG, "downloadTask:done..... ")
        return true
    }

    override fun call(): Boolean {
        return doTask()
    }

    override fun onTaskExecuteSucceed() {
        taskStatus = TaskStatus.TASK_SUCCEED
    }

    override fun onTaskExecuteFailed(message: String) {
        taskStatus = TaskStatus.TASK_FAILED
    }

}