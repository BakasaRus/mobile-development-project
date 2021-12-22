package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import ru.itmo.fitp.mobile.databinding.ActivityDetailsBinding
import ru.itmo.fitp.mobile.databinding.ActivityMainBinding
import ru.itmo.fitp.mobile.iterators.CollatzIterator
import ru.itmo.fitp.mobile.iterators.FibonacciIterator
import ru.itmo.fitp.mobile.iterators.NaturalIterator

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var natural = NaturalIterator()
    private var fibonacci = FibonacciIterator()
    private var collatz = CollatzIterator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val article = intent.getParcelableExtra<Article>("article")
        binding.detailsIcon.setImageResource(article!!.type.getIcon())
        binding.detailsTitle.text = article.title
        binding.detailsDescription.text = article.description

        binding.naturalText.text = natural.next().toString()
        binding.naturalButton.setOnClickListener {
            binding.naturalText.text = natural.next().toString()
        }

        binding.fibonacciText.text = fibonacci.next().toString()
        binding.fibonacciButton.setOnClickListener {
            binding.fibonacciText.text = fibonacci.next().toString()
        }

        binding.collatzText.text = collatz.next().toString()
        binding.collatzButton.setOnClickListener {
            binding.collatzText.text = collatz.next().toString()
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

        binding.naturalText.text = natural.current().toString()
        binding.fibonacciText.text = fibonacci.current().toString()
        binding.collatzText.text = collatz.current().toString()
    }
}