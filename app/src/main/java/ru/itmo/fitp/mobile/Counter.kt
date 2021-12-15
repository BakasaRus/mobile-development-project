package ru.itmo.fitp.mobile

import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Consumer
import kotlin.concurrent.thread

class Counter(delay: Long, isRunning: AtomicBoolean, callback: Consumer<Int>, name: String? = null) {
    private var counter = 0
    private var shouldRun = true

    private val thread = thread(name = name) {
        while (shouldRun) {
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

    fun destroy() {
        shouldRun = false
        thread.join()
    }
}