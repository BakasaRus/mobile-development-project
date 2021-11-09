package ru.itmo.fitp.mobile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val label = findViewById<TextView>(R.id.label)
        label.text = intent.getStringExtra("label")
        val adapter = ArticlesAdapter(this, Article.dummyArticles())
        val listView = findViewById<ListView>(R.id.names_list)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val article = adapter.getItem(position)
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }

        val toastButton = findViewById<Button>(R.id.mainToastButton)
        toastButton.setOnClickListener {
            Toast.makeText(applicationContext, "Hello!", Toast.LENGTH_SHORT).show()
        }

        val hideButton = findViewById<Button>(R.id.hideListButton)
        hideButton.setOnClickListener {
            Log.d("ITMO", "List is hidden now")
            listView.visibility = View.INVISIBLE
        }

        val colorSwitch = findViewById<SwitchMaterial>(R.id.colorSwitch)
        colorSwitch.setOnCheckedChangeListener { button, isChecked ->
            val bar = supportActionBar
            val color = if (isChecked) {
                resources.getColor(R.color.teal_700)
            } else {
                resources.getColor(R.color.purple_500)
            }
            bar?.setBackgroundDrawable(ColorDrawable(color))
        }

        val changeTextFab = findViewById<FloatingActionButton>(R.id.changeTextFab)
        val newLabelTextView = findViewById<EditText>(R.id.newLabelText)
        changeTextFab.setOnClickListener {
            label.text = newLabelTextView.text
        }
    }
}