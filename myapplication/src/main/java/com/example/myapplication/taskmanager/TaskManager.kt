package com.example.myapplication.taskmanager

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
@Author zhang tao
@Date   11/27/22 12:36 AM
@Desc
 */

object TaskManager {
    private val mExecutor = Executors.newFixedThreadPool(10)

    fun <T>submitTask(task: Callable<T>): Future<T> {
        return mExecutor.submit(task)
    }

    fun terminalTasks() {
        if (mExecutor.isShutdown) {
            return
        }
        mExecutor.shutdownNow()
    }
}