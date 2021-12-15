package ru.itmo.fitp.mobile

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Consumer
import kotlin.concurrent.thread

class Counter(delay: Long, isRunning: AtomicBoolean, callback: Consumer<Int>, name: String? = null) {
    private var counter = 0

    @RequiresApi(Build.VERSION_CODES.N)
    private val thread = thread(name = name) {
        while (true) {
            if (!isRunning.get()) {
                continue
            }
            counter++
            callback.accept(counter)
            Thread.sleep(delay)
        }
    }

    fun reset() {
        counter = 0
    }
}