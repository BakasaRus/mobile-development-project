package ru.itmo.fitp.mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ArticlesAdapter(context: Context, articles: ArrayList<Article>) :
    ArrayAdapter<Article>(context, 0, articles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val article = getItem(position)
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)

        val titleTextField = view.findViewById<TextView>(R.id.articleTitle)
        val descTextField = view.findViewById<TextView>(R.id.articleDescription)

        titleTextField.text = article?.title
        descTextField.text = article?.description

        return view
    }
}