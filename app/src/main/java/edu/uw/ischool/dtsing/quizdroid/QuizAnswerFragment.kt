package edu.uw.ischool.dtsing.quizdroid

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

private const val ARG_TOPIC = "topic"
private const val ARG_CURRENT_QUESTION = "currentQuestionNumber"
private const val ARG_USER_ANSWER = "userAnswer"
private const val ARG_CORRECT_INDEX = "correctQuestionIndex"

class QuizAnswerFragment : Fragment() {
    private lateinit var nextBtn: Button
    private lateinit var finishBtn: Button
    private lateinit var topicObject: Topic
    private var topic: String? = null
    private var currentQuestionNumber: Int? = null
    private var userAnswer: Int? = null
    private var correctQuestionIndex: Int? = null

    companion object {
        fun newInstance(topic: String, currentQuestionNumber: Int, userAnswer: Int, correctQuestionIndex: Int): QuizAnswerFragment {
            val fragment = QuizAnswerFragment()
            val args = Bundle().apply {
                putString(ARG_TOPIC, topic)
                putInt(ARG_CURRENT_QUESTION, currentQuestionNumber)
                putInt(ARG_USER_ANSWER, userAnswer)
                putInt(ARG_CORRECT_INDEX, correctQuestionIndex)
            }
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve topic and description from arguments
//        arguments?.let {
//            topic = it.getString(ARG_TOPIC)
//            currentQuestionNumber = it.getInt(ARG_CURRENT_QUESTION)
//            userAnswer = it.getInt(ARG_USER_ANSWER)
//            correctQuestionIndex = it.getInt(ARG_CORRECT_INDEX)
//        }
        arguments?.let {
            topic = it.getString(ARG_TOPIC)
            currentQuestionNumber = it.getInt(ARG_CURRENT_QUESTION)
            userAnswer = it.getInt(ARG_USER_ANSWER)
            correctQuestionIndex = it.getInt(ARG_CORRECT_INDEX)

            if (topic != null) {
                topicObject = (requireActivity().application as QuizApp).topicRepository.getTopic(topic!!)
            } else {
                Toast.makeText(requireContext(), "Topic name is null", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Failed to retrieve topic object")
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quiz_answer, container, false)

        // Check if topicObject is null
        if (topicObject == null) {
            // Handle the case where topicObject is not initialized
            // For example, show an error message and return null or inflate a loading view
            Log.e(TAG, "topicObject is null")
            return null
        }

        val question = topicObject.questionsList[currentQuestionNumber!!]

        // Set user's answer TextView
        view.findViewById<TextView>(R.id.userAnswer).text = question.answers[userAnswer!!]
        view.findViewById<TextView>(R.id.correctAnswer).text = question.answers[question.correctQuestionIndex]
        // Set quiz score text
        view.findViewById<TextView>(R.id.quizScore).text = getString(R.string.quiz_score, correctQuestionIndex)

        // Differentiate between next and finished to decide what button will show up
        val quizStatus = if ((currentQuestionNumber!!) < 5) {
            "Next"
        } else {
            "Finished"
        }

        // Capture reference to the next and finish buttons
        nextBtn = view.findViewById<Button>(R.id.nextBtn).apply {
            text = quizStatus
            visibility = if ((currentQuestionNumber!!) < 5) View.VISIBLE else View.GONE
        }

        finishBtn = view.findViewById<Button>(R.id.finishBtn).apply {
            text = quizStatus
            visibility = if ((currentQuestionNumber!!) == 5) View.VISIBLE else View.GONE
        }

        // Start listening for next fragment after clicking button
        nextBtn.setOnClickListener {
            val fragment = QuizQuestionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CURRENT_QUESTION, currentQuestionNumber!! + 1)
                    putInt(ARG_CORRECT_INDEX, question.correctQuestionIndex)
                    putString(ARG_TOPIC, topic)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }

        finishBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        return view
    }
}