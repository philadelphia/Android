package com.example.myapplication.taskmanager

import java.util.concurrent.Callable

/**
@Author zhang tao
@Date   11/27/22 12:40 AM
@Desc
 */
abstract class BaseTask<T>(val url: String) : Callable<T>, ITask {
    var taskStatus: TaskStatus? = null
}

enum class TaskStatus {
    TASK_STATED,
    TASK_SUCCEED,
    TASK_FAILED,
    TASK_CANCELED
}