package ru.itmo.fitp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

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
    }
}