package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class ThreadActivity : AppCompatActivity() {
    private var isRunning = AtomicBoolean(false)
    private lateinit var slowCounterLabel: TextView
    private var slowCounter = 0
    private var fastCounter = 0
    private lateinit var fastCounterLabel: TextView
    private lateinit var runButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    val slowCounterThread = thread(name = "SlowCounter") {
        while (true) {
            if (!isRunning.get()) {
                continue
            }
            slowCounter++
            runOnUiThread {
                slowCounterLabel.text = slowCounter.toString()
            }
            Thread.sleep(600)
        }
    }

    val fastCounterThread = thread(name = "FastCounter") {
        while (true) {
            if (!isRunning.get()) {
                continue
            }
            fastCounter++
            runOnUiThread {
                fastCounterLabel.text = fastCounter.toString()
            }
            Thread.sleep(400)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        slowCounterLabel = findViewById(R.id.slowIncLabel)
        fastCounterLabel = findViewById(R.id.fastIncLabel)

        runButton = findViewById(R.id.runCounterButton)
        runButton.setOnClickListener {
            isRunning.set(true)
        }

        stopButton = findViewById(R.id.stopCounterButton)
        stopButton.setOnClickListener {
            isRunning.set(false)
        }

        resetButton = findViewById(R.id.resetCounterButton)
        resetButton.setOnClickListener {
            isRunning.set(false)
            fastCounter = 0
            fastCounterLabel.text = fastCounter.toString()
            slowCounter = 0
            slowCounterLabel.text = slowCounter.toString()
        }
    }
}