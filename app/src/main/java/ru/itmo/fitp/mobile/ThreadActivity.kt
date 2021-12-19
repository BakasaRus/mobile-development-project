package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.min
import kotlin.math.max

class ThreadActivity : AppCompatActivity() {
    private var isRunning = AtomicBoolean(false)

    private lateinit var runButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    private var slowDelay = 600L
    private lateinit var slowCounter: Counter
    private lateinit var slowCounterLabel: TextView
    private lateinit var slowDelayLabel: TextView
    private lateinit var slowDelayDecButton: Button
    private lateinit var slowDelayIncButton: Button

    private var fastDelay = 400L
    private lateinit var fastCounter: Counter
    private lateinit var fastCounterLabel: TextView
    private lateinit var fastDelayLabel: TextView
    private lateinit var fastDelayDecButton: Button
    private lateinit var fastDelayIncButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        slowCounterLabel = findViewById(R.id.slowCounterLabel)
        renderCounter(0, slowCounterLabel)
        slowCounter = Counter(slowDelay, isRunning) {
            renderCounter(it, slowCounterLabel)
            Log.d("ITMO", "Slow Counter ${Thread.currentThread().id}")
        }

        slowDelayLabel = findViewById(R.id.slowDelayLabel)
        slowDelayLabel.text = "${slowDelay} ms"
        slowDelayDecButton = findViewById(R.id.slowDelayDecButton)
        slowDelayIncButton = findViewById(R.id.slowDelayIncButton)
        slowDelayDecButton.setOnClickListener {
            slowDelay = max(slowDelay - 50, 50)
            slowDelayLabel.text = "${slowDelay} ms"
        }
        slowDelayIncButton.setOnClickListener {
            slowDelay = min(slowDelay + 50, 2000)
            slowDelayLabel.text = "${slowDelay} ms"
        }

        fastCounterLabel = findViewById(R.id.fastCounterLabel)
        renderCounter(0, fastCounterLabel)
        fastCounter = Counter(fastDelay, isRunning) {
            renderCounter(it, fastCounterLabel)
            Log.d("ITMO", "Fast Counter ${Thread.currentThread().id}")
        }

        fastDelayLabel = findViewById(R.id.fastDelayLabel)
        fastDelayLabel.text = "${fastDelay} ms"
        fastDelayDecButton = findViewById(R.id.fastDelayDecButton)
        fastDelayIncButton = findViewById(R.id.fastDelayIncButton)
        fastDelayDecButton.setOnClickListener {
            fastDelay = max(fastDelay - 50, 50)
            fastDelayLabel.text = "${fastDelay} ms"
        }
        fastDelayIncButton.setOnClickListener {
            fastDelay = min(fastDelay + 50, 2000)
            fastDelayLabel.text = "${fastDelay} ms"
        }

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
            renderCounter(0, fastCounterLabel)
            slowCounter.reset()
            renderCounter(0, slowCounterLabel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fastCounter.destroy()
        slowCounter.destroy()
    }

    private fun renderCounter(value: Int, view: TextView) = runOnUiThread {
        view.text = value.toString()
    }
}