package edu.uw.ischool.dtsing.quizdroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TopicListAdapter(context: Context, private val topics: List<Topic>) : ArrayAdapter<Topic>(context, 0, topics) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        }

        val titleTextView: TextView? = itemView?.findViewById(android.R.id.text1) as? TextView
        val descriptionTextView: TextView? = itemView?.findViewById(android.R.id.text2) as? TextView

        if (titleTextView != null && descriptionTextView != null) {
            val topic = topics[position]
            titleTextView.text = topic.title
            descriptionTextView.text = topic.shortDescription
        }

        return itemView ?: View(context) // Return an empty view if itemView is null
    }
}
