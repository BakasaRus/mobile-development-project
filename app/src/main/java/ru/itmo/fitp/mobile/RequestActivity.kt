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
import android.widget.Toast
import com.squareup.picasso.Picasso
import okhttp3.logging.HttpLoggingInterceptor
import ru.itmo.fitp.mobile.databinding.ActivityRequestBinding

class RequestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestBinding

    private lateinit var client: OkHttpClient
    private lateinit var logging: HttpLoggingInterceptor
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        client = OkHttpClient.Builder().addInterceptor(logging).build()

        getRandomGame()
        binding.getGameButton.setOnClickListener { getRandomGame() }
    }

    private fun getRandomGame() {
        val request = Request.Builder()
            .url("http://192.168.0.108:3000/games/random")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(applicationContext, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        runOnUiThread {
                            Toast.makeText(applicationContext, getString(R.string.no_connection_to_server), Toast.LENGTH_LONG).show()
                        }
                        return
                    }

                    val game = gson.fromJson( response.body!!.string(), Game::class.java)
                    Log.d("ITMO", game.id)
                    runOnUiThread {
                        Picasso.get().load(game.image).into(binding.gameImage)
                        binding.titleLabel.text = game.title
                        binding.descriptionLabel.text = game.description
                        binding.releaseDateLabel.text = resources.getString(R.string.release_date, game.prettyDateReleased)
                        binding.publisherLabel.text = resources.getString(R.string.publisher, game.publisher)
                        binding.priceLabel.text = resources.getString(R.string.price, game.price)
                        binding.openGameButton.setOnClickListener {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://nintendo.ru${game.url}"))
                            startActivity(browserIntent)
                        }
                    }
                }
            }
        })
    }
}