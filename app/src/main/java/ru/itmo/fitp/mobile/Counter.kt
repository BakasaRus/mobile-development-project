package ru.itmo.fitp.mobile

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class Counter(@Volatile private var delay: Long, isRunning: AtomicBoolean, renderMethod: (Int) -> Unit) {
    private var counter = 0
    private var shouldRun = true
    private var shouldReset = false

    private val thread = thread {
        while (shouldRun) {
            if (shouldReset) {
                counter = 0
                shouldReset = false
            }
            if (!isRunning.get()) {
                continue
            }
            counter++
            renderMethod(counter)
            Thread.sleep(delay)
        }
    }

    fun reset() {
        shouldReset = true
    }

    fun changeDelay(newDelay: Long) {
        this.delay = newDelay
    }

    fun destroy() {
        shouldRun = false
        thread.join()
    }
}