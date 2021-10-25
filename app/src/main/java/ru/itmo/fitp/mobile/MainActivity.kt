package ru.itmo.fitp.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

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
    }
}