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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import android.view.MenuItem

import androidx.annotation.NonNull
import com.google.android.material.navigation.NavigationView
import ru.itmo.fitp.mobile.databinding.ActivityInitialBinding
import ru.itmo.fitp.mobile.databinding.ActivityRequestBinding

class InitialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitialBinding
    private lateinit var pointsKey: String
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        drawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close)

        binding.myDrawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pointsKey = getString(R.string.pointsPrefKey)

        var points = getPreferences(Context.MODE_PRIVATE).getInt(pointsKey, 0)
        applyPointsChange(points)

        binding.plusButton.setOnClickListener {
            points++
            applyPointsChange(points)
        }
        binding.minusButton.setOnClickListener {
            points--
            applyPointsChange(points)
        }

        binding.navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.next_activity_button -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("label", "Hello from InitialActivity")
                    startActivity(intent)
                    true
                }
                R.id.thread_activity_button -> {
                    val intent = Intent(this, ThreadActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.request_activity_button -> {
                    val intent = Intent(this, RequestActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.toast_button -> {
                    Toast.makeText(applicationContext, "Test", Toast.LENGTH_SHORT).show()
                    Log.d("ITMO", "Test")
                    true
                }
                else -> { false }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun applyPointsChange(points: Int) {
        binding.pointsField.text = points.toString()
        getPreferences(Context.MODE_PRIVATE).edit().putInt(pointsKey, points).apply()
        binding.plusButton.isEnabled = points < 100
        binding.minusButton.isEnabled = points > 0
    }
}