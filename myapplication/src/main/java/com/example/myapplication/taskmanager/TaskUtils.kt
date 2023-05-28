package com.example.myapplication.taskmanager

import android.util.Log
import com.example.myapplication.taskmanager.task.DownloadTask

/**
@Author zhang tao
@Date   11/27/22 12:54 AM
@Desc
 */
class TaskUtils {
    companion object {
        private const val TAG = "TaskUtils"
        fun executeDownloadTask(url: String) {
            val downloadTask = DownloadTask(url)
            val submitTask = TaskManager.submitTask(downloadTask)
            var get = submitTask.get() as Boolean
            if (get) {
                Log.d(TAG, "executeDownloadTask: task succeed")
            } else {
                Log.d(TAG, "executeDownloadTask: task failed")
            }
        }
    }
}