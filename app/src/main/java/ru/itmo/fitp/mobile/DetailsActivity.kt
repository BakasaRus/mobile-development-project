package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random
import kotlin.random.nextUInt

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val article = intent.getParcelableExtra<Article>("article")

        val iconView = findViewById<ImageView>(R.id.detailsIcon)
        iconView.setImageResource(article!!.type.getIcon())

        val titleView = findViewById<TextView>(R.id.detailsTitle)
        titleView.text = article.title

        val descriptionView = findViewById<TextView>(R.id.detailsDescription)
        descriptionView.text = article.description

        val naturalButton = findViewById<Button>(R.id.naturalButton)
        val naturalText = findViewById<TextView>(R.id.naturalText)
        naturalText.text = natural.next().toString()
        naturalButton.setOnClickListener {
            naturalText.text = natural.next().toString()
        }

        val fibonacciButton = findViewById<Button>(R.id.fibonacciButton)
        val fibonacciText = findViewById<TextView>(R.id.fibonacciText)
        fibonacciText.text = fibonacci.next().toString()
        fibonacciButton.setOnClickListener {
            fibonacciText.text = fibonacci.next().toString()
        }

        val collatzButton = findViewById<Button>(R.id.collatzButton)
        val collatzText = findViewById<TextView>(R.id.collatzText)
        collatzText.text = collatz.next().toString()
        collatzButton.setOnClickListener {
            collatzText.text = collatz.next().toString()
        }
    }

    private val natural = iterator {
        var term = 0
        while (true) {
            yield(term)
            term++
            if (term < 0)
                term = 0
        }
    }

    private val fibonacci = iterator {
        var terms = Pair(0, 1)
        while (true) {
            yield(terms.first)
            terms = Pair(terms.second, terms.first + terms.second)
            if (terms.second < 0) {
                terms = Pair(0, 1)
            }
        }
    }

    private val collatz = iterator {
        var term = Random(System.currentTimeMillis()).nextUInt() % 1000U
        while (true) {
            yield(term)
            term = if (term % 2U == 0U) {
                term / 2U
            } else {
                term * 3U + 1U
            }
        }
    }
}