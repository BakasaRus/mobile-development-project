package ru.itmo.fitp.mobile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val label = findViewById<TextView>(R.id.label)
        label.text = intent.getStringExtra("label")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.names))
        val listView = findViewById<ListView>(R.id.names_list)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
//            val element = adapter.getItemAtPosition(position) // The item that was clicked
//            val intent = Intent(this, BookDetailActivity::class.java)
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        val toastButton = findViewById<Button>(R.id.mainToastButton)
        toastButton.setOnClickListener {
            Toast.makeText(applicationContext, "Hello!", Toast.LENGTH_SHORT).show()
        }

        val colorSwitch = findViewById<SwitchMaterial>(R.id.colorSwitch)
        colorSwitch.setOnCheckedChangeListener { button, isChecked ->
            val bar = supportActionBar
            if (isChecked) {
                bar?.setBackgroundDrawable(ColorDrawable(Color.CYAN))
            } else {
                bar?.setBackgroundDrawable(ColorDrawable(Color.RED))
            }
        }
    }
}