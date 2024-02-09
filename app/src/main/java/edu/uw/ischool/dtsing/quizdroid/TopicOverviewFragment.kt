package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TopicOverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_topic_overview, container, false)

        // Reference button
        val beginQuizBtn = view.findViewById<Button>(R.id.beginQuizBtn)

        // TODO: Fix topic title not properly showing up

        // Listen for click events on button
        beginQuizBtn.setOnClickListener{
            // When the button is clicked, navigate to QuizQuestionFragment
            (requireActivity() as MainActivity).navToQuizQuestion(1, 0)
        }

        return view
    }
}