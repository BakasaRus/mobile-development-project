package ru.itmo.fitp.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
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