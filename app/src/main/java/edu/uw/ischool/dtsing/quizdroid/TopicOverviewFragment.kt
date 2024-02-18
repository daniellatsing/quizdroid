package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class TopicOverviewFragment : Fragment() {
    private var topic: String? = null
    private var description: String? = null

    companion object {
        const val ARG_TOPIC = "topic"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(topic: String, description: String): TopicOverviewFragment {
            val fragment = TopicOverviewFragment()
            val args = Bundle().apply {
                putString(ARG_TOPIC, topic)
                putString(ARG_DESCRIPTION, description)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve topic and description from arguments
        arguments?.let {
            topic = it.getString(ARG_TOPIC)
            description = it.getString(ARG_DESCRIPTION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_topic_overview, container, false)

        // Display the topic title and description
        val topicTextView = view.findViewById<TextView>(R.id.topicTextView)
        topicTextView.text = topic

        val descTextView = view.findViewById<TextView>(R.id.topicDescriptionTextView)
        descTextView.text = description

        // Reference button
        val beginQuizBtn = view.findViewById<Button>(R.id.beginQuizBtn)
        val backBtn = view.findViewById<Button>(R.id.backBtn)

        // Listen for click events on button
        beginQuizBtn.setOnClickListener{
            // When the button is clicked, navigate to QuizQuestionFragment
            topic?.let { topicName ->
                (requireActivity() as? MainActivity)?.navToQuizQuestion(topicName, 0)
            }

            Log.i("Button message", "Begin button pressed")
        }

        backBtn.setOnClickListener{
            // When the button is clicked, navigate to list of topics
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            Log.i("Button message", "Back button pressed")
        }

        return view
    }
}