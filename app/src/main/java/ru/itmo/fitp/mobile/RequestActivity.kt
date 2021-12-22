package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import android.net.Uri

import android.content.Intent
import android.widget.ImageView
import com.squareup.picasso.Picasso

class RequestActivity : AppCompatActivity() {
    private lateinit var titleLabel: TextView
    private lateinit var descriptionLabel: TextView
    private lateinit var getAnotherGameButton: Button
    private lateinit var openGameButton: Button
    private lateinit var gameImage: ImageView

    private val client = OkHttpClient()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        titleLabel = findViewById(R.id.titleLabel)
        descriptionLabel = findViewById(R.id.descriptionLabel)
        getAnotherGameButton = findViewById(R.id.getGameButton)
        openGameButton = findViewById(R.id.openGameButton)
        gameImage = findViewById(R.id.gameImage)

        getRandomGame()

        getAnotherGameButton.setOnClickListener { getRandomGame() }
    }

    private fun getRandomGame() {
        val request = Request.Builder()
            .url("http://192.168.0.108:3000/games/random")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }

                    val game = gson.fromJson( response.body!!.string(), Game::class.java)
                    Log.d("ITMO", game.id)
                    runOnUiThread {
                        Picasso.get().load(game.image).into(gameImage)
                        titleLabel.text = game.title
                        descriptionLabel.text = game.description
                        openGameButton.setOnClickListener {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://nintendo.ru${game.url}"))
                            startActivity(browserIntent)
                        }
                    }
                }
            }
        })
    }
}