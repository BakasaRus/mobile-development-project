package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import java.io.IOException

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val client = OkHttpClient()

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

                    for ((name, value) in response.headers) {
                        Log.d("ITMO", "$name: $value")
                    }

                    Log.d("ITMO", response.body!!.string())
                }
            }
        })
    }
}