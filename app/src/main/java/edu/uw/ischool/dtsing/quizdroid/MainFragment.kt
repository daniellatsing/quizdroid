package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private lateinit var rvList: RecyclerView

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
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Reference RecyclerView
        rvList = view.findViewById(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(requireContext())

        val adapter = RVAdapter(topics) { topic ->
            // Handle item click, navigate to TopicOverviewFragment with selected topic
            (requireActivity() as MainActivity).navToTopicOverview(topic)
        }
        rvList.adapter = adapter

        return view
    }

}