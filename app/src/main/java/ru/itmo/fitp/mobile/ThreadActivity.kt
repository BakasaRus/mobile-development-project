package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import ru.itmo.fitp.mobile.databinding.ActivityThreadBinding
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.min
import kotlin.math.max

class ThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadBinding
    private var isRunning = AtomicBoolean(false)

    @Volatile
    private var slowDelay = 600L
    private lateinit var slowCounter: Counter

    @Volatile
    private var fastDelay = 400L
    private lateinit var fastCounter: Counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        renderCounter(0, binding.slowCounterLabel)
        slowCounter = Counter(slowDelay, isRunning) {
            renderCounter(it, binding.slowCounterLabel)
            Log.d("ITMO", "Slow Counter ${Thread.currentThread().id}")
        }

        binding.slowDelayLabel.text = resources.getString(R.string.some_ms, slowDelay)
        binding.slowDelayDecButton.setOnClickListener {
            slowDelay = max(slowDelay - 50, 50)
            slowCounter.changeDelay(slowDelay)
            binding.slowDelayLabel.text = resources.getString(R.string.some_ms, slowDelay)
        }
        binding.slowDelayIncButton.setOnClickListener {
            slowDelay = min(slowDelay + 50, 2000)
            slowCounter.changeDelay(slowDelay)
            binding.slowDelayLabel.text = resources.getString(R.string.some_ms, slowDelay)
        }

        renderCounter(0, binding.fastCounterLabel)
        fastCounter = Counter(fastDelay, isRunning) {
            renderCounter(it, binding.fastCounterLabel)
            Log.d("ITMO", "Fast Counter ${Thread.currentThread().id}")
        }

        binding.fastDelayLabel.text = resources.getString(R.string.some_ms, fastDelay)
        binding.fastDelayDecButton.setOnClickListener {
            fastDelay = max(fastDelay - 50, 50)
            fastCounter.changeDelay(fastDelay)
            binding.fastDelayLabel.text = resources.getString(R.string.some_ms, fastDelay)
        }
        binding.fastDelayIncButton.setOnClickListener {
            fastDelay = min(fastDelay + 50, 2000)
            fastCounter.changeDelay(fastDelay)
            binding.fastDelayLabel.text = resources.getString(R.string.some_ms, fastDelay)
        }

        binding.runCounterButton.setOnClickListener {
            isRunning.set(true)
        }

        binding.stopCounterButton.setOnClickListener {
            isRunning.set(false)
        }

        binding.resetCounterButton.setOnClickListener {
            isRunning.set(false)
            fastCounter.reset()
            renderCounter(0, binding.fastCounterLabel)
            slowCounter.reset()
            renderCounter(0, binding.slowCounterLabel)
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