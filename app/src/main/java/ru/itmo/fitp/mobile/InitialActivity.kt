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
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var pointsField: TextView
    private lateinit var pointsKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        pointsField = findViewById(R.id.pointsField)
        plusButton = findViewById(R.id.plusButton)
        minusButton = findViewById(R.id.minusButton)
        pointsKey = getString(R.string.pointsPrefKey)

        var points = getPreferences(Context.MODE_PRIVATE).getInt(pointsKey, 0)
        applyPointsChange(points)

        plusButton.setOnClickListener {
            points++
            applyPointsChange(points)
        }
        minusButton.setOnClickListener {
            points--
            applyPointsChange(points)
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

    private fun applyPointsChange(points: Int) {
        pointsField.text = points.toString()
        getPreferences(Context.MODE_PRIVATE).edit().putInt(pointsKey, points).apply()
        plusButton.isEnabled = points < 100
        minusButton.isEnabled = points > 0
    }
}