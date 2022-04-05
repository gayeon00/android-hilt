package com.example.android.hilt.data

import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

/**
 * - 메모리에 로그를 저장하는 LoggerDataSource의 또 다른 구현
 */
//LoggerInMemoryDataSource의 범위를 Activity 컨테이너로 지정
class LoggerInMemoryDataSource @Inject constructor() : LoggerDataSource{

    private val logs = LinkedList<Log>()

    override fun addLog(msg: String) {
        logs.addFirst(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        logs.clear()
    }

}