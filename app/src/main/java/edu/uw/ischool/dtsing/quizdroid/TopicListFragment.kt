package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.io.File

class TopicListFragment : Fragment() {
    private lateinit var topicListView: ListView
    private lateinit var topicRepository: TopicRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_topic_list, container, false)
        topicListView = view.findViewById(R.id.topicListView)

        val jsonFile = File(requireContext().filesDir, "dtsing_custom_questions.json")
        topicRepository = TopicRepository(jsonFile)

        val topics = topicRepository.getAllTopics()

        val adapter = TopicListAdapter(requireContext(), topics)
        topicListView.adapter = adapter

        // Set item click listener
        topicListView.setOnItemClickListener { _, _, position, _ ->
            val selectedTopic = topics[position]
            Log.i("TOPIC", "Topic Selected")
            navToTopicOverview(selectedTopic.title, selectedTopic.longDescription)
        }

        return view
    }

    private fun navToTopicOverview(topic: String, longDescription: String) {
        val fragment = TopicOverviewFragment.newInstance(topic, longDescription)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}