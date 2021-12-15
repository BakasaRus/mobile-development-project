package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.atomic.AtomicBoolean

class ThreadActivity : AppCompatActivity() {
    private var isRunning = AtomicBoolean(false)
    private lateinit var slowCounterLabel: TextView
    private lateinit var fastCounterLabel: TextView

    private lateinit var runButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    private lateinit var slowCounter: Counter
    private lateinit var fastCounter: Counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        slowCounterLabel = findViewById(R.id.slowIncLabel)
        fastCounterLabel = findViewById(R.id.fastIncLabel)
        renderCounter(0, fastCounterLabel)
        renderCounter(0, slowCounterLabel)

        slowCounter = Counter(600, isRunning, {
            renderCounter(it, slowCounterLabel)
        })

        fastCounter = Counter(400, isRunning, {
            renderCounter(it, fastCounterLabel)
        })

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
            fastCounter.reset()
            slowCounter.reset()
            renderCounter(0, fastCounterLabel)
            renderCounter(0, slowCounterLabel)
        }
    }

    private fun renderCounter(value: Int, view: TextView) = runOnUiThread {
        view.text = value.toString()
    }
}