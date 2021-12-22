package ru.itmo.fitp.mobile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import ru.itmo.fitp.mobile.databinding.ActivityInitialBinding
import ru.itmo.fitp.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.label.text = intent.getStringExtra("label")
        val adapter = ArticlesAdapter(this, Article.dummyArticles())
        binding.namesList.adapter = adapter

        binding.namesList.setOnItemClickListener { parent, view, position, id ->
            val article = adapter.getItem(position)
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }

        binding.mainToastButton.setOnClickListener {
            Toast.makeText(applicationContext, "Hello!", Toast.LENGTH_SHORT).show()
        }

        binding.hideListButton.setOnClickListener {
            Log.d("ITMO", "List is hidden now")
            binding.namesList.visibility = View.INVISIBLE
        }

        binding.colorSwitch.setOnCheckedChangeListener { button, isChecked ->
            val bar = supportActionBar
            val color = if (isChecked) {
                resources.getColor(R.color.teal_700)
            } else {
                resources.getColor(R.color.purple_500)
            }
            bar?.setBackgroundDrawable(ColorDrawable(color))
        }

        binding.changeTextFab.setOnClickListener {
            binding.label.text = binding.newLabelText.text
            Snackbar.make(binding.coordinator, getString(R.string.hello_snackbar), Snackbar.LENGTH_LONG).show()
        }
    }
}