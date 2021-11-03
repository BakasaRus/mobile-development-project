package ru.itmo.fitp.mobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.forEach

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        var points = sharedPreferences.getInt("points", 0)
        val pointsField = findViewById<TextView>(R.id.pointsField)
        pointsField.text = points.toString()

        val plusButton = findViewById<Button>(R.id.plusButton)
        val minusButton = findViewById<Button>(R.id.minusButton)

        plusButton.setOnClickListener {
            points++
            pointsField.text = points.toString()
            sharedPreferences.edit().putInt("points", points).apply()
            plusButton.isEnabled = points < 100
            minusButton.isEnabled = points > 0
        }
        minusButton.setOnClickListener {
            points--
            pointsField.text = points.toString()
            sharedPreferences.edit().putInt("points", points).apply()
            plusButton.isEnabled = points < 100
            minusButton.isEnabled = points > 0
        }

        val button = findViewById<Button>(R.id.toast_button)
        button.setOnClickListener {
            Toast.makeText(applicationContext, "Test", Toast.LENGTH_SHORT).show()
            Log.d("ITMO", "Test")
        }

        val next = findViewById<Button>(R.id.next_activity_button)
        next.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("label", "Hello from InitialActivity")
            startActivity(intent)
        }
    }
}