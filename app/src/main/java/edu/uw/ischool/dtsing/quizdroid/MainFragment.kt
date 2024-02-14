package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment


class MainFragment : Fragment() {

    private lateinit var shortDescriptionTextView: TextView
    private lateinit var listView: ListView
    private lateinit var topics: List<Topic>

    companion object {
        fun newInstance(topics: List<Topic>): MainFragment {
            val fragment = MainFragment()
            fragment.topics = topics
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Reference ListView
        listView = view.findViewById(R.id.listView)

        shortDescriptionTextView = view.findViewById(R.id.subtitleTextView)

        val adapter = TopicListAdapter(requireContext(), topics) { topicTitle, _ ->
            val selectedTopic = topics.find { it.title == topicTitle }
            selectedTopic?.let {
                shortDescriptionTextView.text = it.shortDescription
                (requireActivity() as MainActivity).navToTopicOverview(it.title, it.shortDescription)
            }
        }
        listView.adapter = adapter

        return view
    }
}