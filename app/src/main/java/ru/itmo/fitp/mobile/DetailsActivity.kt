package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import ru.itmo.fitp.mobile.iterators.CollatzIterator
import ru.itmo.fitp.mobile.iterators.FibonacciIterator
import ru.itmo.fitp.mobile.iterators.NaturalIterator

class DetailsActivity : AppCompatActivity() {
    private var natural = NaturalIterator()
    private var fibonacci = FibonacciIterator()
    private var collatz = CollatzIterator()

    private lateinit var iconView: ImageView
    private lateinit var titleView: TextView
    private lateinit var descriptionView: TextView

    private lateinit var naturalButton: Button
    private lateinit var naturalText: TextView
    private lateinit var fibonacciButton: Button
    private lateinit var fibonacciText: TextView
    private lateinit var collatzButton: Button
    private lateinit var collatzText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val article = intent.getParcelableExtra<Article>("article")

        iconView = findViewById(R.id.detailsIcon)
        iconView.setImageResource(article!!.type.getIcon())

        titleView = findViewById(R.id.detailsTitle)
        titleView.text = article.title

        descriptionView = findViewById(R.id.detailsDescription)
        descriptionView.text = article.description

        naturalButton = findViewById(R.id.naturalButton)
        naturalText = findViewById(R.id.naturalText)
        naturalText.text = natural.next().toString()
        naturalButton.setOnClickListener {
            naturalText.text = natural.next().toString()
        }

        fibonacciButton = findViewById(R.id.fibonacciButton)
        fibonacciText = findViewById(R.id.fibonacciText)
        fibonacciText.text = fibonacci.next().toString()
        fibonacciButton.setOnClickListener {
            fibonacciText.text = fibonacci.next().toString()
        }

        collatzButton = findViewById(R.id.collatzButton)
        collatzText = findViewById(R.id.collatzText)
        collatzText.text = collatz.next().toString()
        collatzButton.setOnClickListener {
            collatzText.text = collatz.next().toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("natural", natural)
        outState.putSerializable("fibonacci", fibonacci)
        outState.putSerializable("collatz", collatz)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        natural = savedInstanceState.getSerializable("natural") as NaturalIterator
        fibonacci = savedInstanceState.getSerializable("fibonacci") as FibonacciIterator
        collatz = savedInstanceState.getSerializable("collatz") as CollatzIterator

        naturalText.text = natural.current().toString()
        fibonacciText.text = fibonacci.current().toString()
        collatzText.text = collatz.current().toString()
    }
}