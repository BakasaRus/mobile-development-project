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

class InitialActivity : AppCompatActivity() {
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var pointsField: TextView
    private lateinit var pointsKey: String
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        navigationView = findViewById(R.id.navigation)
        navigationView.setNavigationItemSelectedListener {
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
        pointsField.text = points.toString()
        getPreferences(Context.MODE_PRIVATE).edit().putInt(pointsKey, points).apply()
        plusButton.isEnabled = points < 100
        minusButton.isEnabled = points > 0
    }
}