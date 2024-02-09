package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class TopicOverviewFragment : Fragment() {
    companion object {
        private const val ARG_TOPIC = "topic"

        fun newInstance(topic: String): TopicOverviewFragment {
            val fragment = TopicOverviewFragment()
            val args = Bundle()
            args.putString(ARG_TOPIC, topic)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_topic_overview, container, false)

        // Retrieve topic title from arguments
        val topic = arguments?.getString(ARG_TOPIC, "") ?: ""

        // Display the topic title (e.g., set it to a TextView)
        val topicTextView = view.findViewById<TextView>(R.id.topicTextView)
        topicTextView.text = topic

        // Reference button
        val beginQuizBtn = view.findViewById<Button>(R.id.beginQuizBtn)
        val backBtn = view.findViewById<Button>(R.id.backBtn)

        // Listen for click events on button
        beginQuizBtn.setOnClickListener{
            // When the button is clicked, navigate to QuizQuestionFragment
            (requireActivity() as MainActivity).navToQuizQuestion(1, 0, topic)
        }

        backBtn.setOnClickListener{
            // When the button is clicked, navigate to list of topics
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}