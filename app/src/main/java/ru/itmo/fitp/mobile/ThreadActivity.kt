package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.concurrent.thread

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        val slowCounterLabel = findViewById<TextView>(R.id.slowIncLabel)
        val fastCounterLabel = findViewById<TextView>(R.id.fastIncLabel)

        val slowCounter = thread(name = "SlowCounter") {
            var counter = 0
            while (true) {
                counter++
                runOnUiThread {
                    slowCounterLabel.text = counter.toString()
                }
                Thread.sleep(600)
            }
        }

        val fastCounter = thread(name = "FastCounter") {
            var counter = 0
            while (true) {
                counter++
                runOnUiThread {
                    fastCounterLabel.text = counter.toString()
                }
                Thread.sleep(400)
            }
        }
    }
}