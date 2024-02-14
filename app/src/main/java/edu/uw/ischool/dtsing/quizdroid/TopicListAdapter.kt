package edu.uw.ischool.dtsing.quizdroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TopicListAdapter(
    context: Context,
    topics: List<Topic>,
    private val listener: (String, Any?) -> Unit
) : ArrayAdapter<Topic>(context, 0, topics) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val holder: ViewHolder
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
            holder = ViewHolder(itemView)
            itemView.tag = holder
        } else {
            holder = itemView.tag as ViewHolder
        }

        val topic = getItem(position)
        if (topic != null) {
            holder.shortDescriptionTextView.text = topic.shortDescription
        }
        topic?.let { holder.bindItem(it, itemView!!) }

        return itemView!!
    }

    inner class ViewHolder(itemView: View) {
        val shortDescriptionTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
        private val textView: TextView = itemView.findViewById(R.id.titleTextView)
        private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)

        fun bindItem(topic: Topic?, itemView: View) {
            textView.text = topic?.title
            subtitleTextView.text = topic?.shortDescription

            itemView.setOnClickListener {
                topic?.let { listener(it.title, it.longDescription) }
            }
        }
    }
}