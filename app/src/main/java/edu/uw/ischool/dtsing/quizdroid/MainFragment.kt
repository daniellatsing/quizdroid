package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private lateinit var rvList: RecyclerView

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

        // Hard-coded list of sample topic names
        val data = listOf(
            "Math",
            "Physics",
            "Biology",
            "Pokemon Types",
            "League of Legends",
        )

        rvList.layoutManager = LinearLayoutManager(requireContext())
        rvList.adapter = RVAdapter(data) { topic ->
            // Handle item click, navigate to TopicOverviewFragment with selected topic
            (requireActivity() as MainActivity).navToTopicOverview(topic)
        }

        return view
    }

}