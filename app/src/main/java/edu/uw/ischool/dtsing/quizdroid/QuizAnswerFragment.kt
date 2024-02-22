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
import java.io.File

private const val ARG_TOPIC_NAME = "topicName"
private const val ARG_CURRENT_QUESTION = "currentQuestionNumber"
private const val ARG_USER_ANSWER = "userAnswer"
private const val ARG_CORRECT_INDEX = "correctQuestionIndex"
private const val ARG_NUM_CORRECT_ANSWERS = "numCorrectAnswers"

class QuizAnswerFragment : Fragment() {
    private lateinit var nextBtn: Button
    private lateinit var finishBtn: Button
    private lateinit var topicRepository: TopicRepository
    private var topicObject: Topic? = null
    private var topicName: String? = null
    private var currentQuestionNumber: Int? = null
    private var userAnswer: Int? = null
    private var correctQuestionIndex: Int? = null
    private var numCorrectAnswers: Int = 0

    companion object {
        fun newInstance(
            topicName: String,
            currentQuestionNumber: Int,
            userAnswer: Int,
            correctQuestionIndex: Int,
            numCorrectAnswers: Int
        ): QuizAnswerFragment {
            val fragment = QuizAnswerFragment()
            val args = Bundle().apply {
                putString(ARG_TOPIC_NAME, topicName)
                putInt(ARG_CURRENT_QUESTION, currentQuestionNumber)
                putInt(ARG_USER_ANSWER, userAnswer)
                putInt(ARG_CORRECT_INDEX, correctQuestionIndex)
                putInt(ARG_NUM_CORRECT_ANSWERS, numCorrectAnswers)
            }
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonFile = File(requireContext().filesDir, "dtsing_custom_questions.json")
        topicRepository = TopicRepository(jsonFile)

        // Retrieve topic and description from arguments
        arguments?.let {
            topicName = it.getString(ARG_TOPIC_NAME)
            currentQuestionNumber = it.getInt(ARG_CURRENT_QUESTION)
            userAnswer = it.getInt(ARG_USER_ANSWER)
            correctQuestionIndex = it.getInt(ARG_CORRECT_INDEX)
            numCorrectAnswers = it.getInt(ARG_NUM_CORRECT_ANSWERS)

            if (topicName != null) {
                topicObject =  topicRepository.getTopic(topicName!!)
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
            Log.e(TAG, "topicObject is null")
            return null
        }

        val question = topicObject?.questionsList?.getOrNull(currentQuestionNumber!!)

        // Set user's answer TextView
        if (question != null) {
            view.findViewById<TextView>(R.id.userAnswer).text = question.answers[userAnswer!!]
        }
        if (question != null) {
            view.findViewById<TextView>(R.id.correctAnswer).text = question.answers[question.correctQuestionIndex]
        }

        // Set quiz score text
        view.findViewById<TextView>(R.id.quizScore).text = getString(R.string.quiz_score, numCorrectAnswers)

        if (question != null) {
            if (userAnswer == question.correctQuestionIndex) {
                (activity as? MainActivity)?.numCorrectAnswers = (activity as? MainActivity)?.numCorrectAnswers!! + 1
            }
        }

        // Differentiate between next and finished to decide what button will show up
        val quizStatus = if ((currentQuestionNumber!!) < 4) "Next" else "Finish"

        // Capture reference to the next and finish buttons
        nextBtn = view.findViewById<Button>(R.id.nextBtn).apply {
            text = quizStatus
            visibility = if ((currentQuestionNumber!!) < 4) View.VISIBLE else View.GONE
        }

        finishBtn = view.findViewById<Button>(R.id.finishBtn).apply {
            text = quizStatus
            visibility = if ((currentQuestionNumber!!) == 4) View.VISIBLE else View.GONE
        }

        // Start listening for next fragment after clicking button
        nextBtn.setOnClickListener {
            val fragment = QuizQuestionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CURRENT_QUESTION, currentQuestionNumber!! + 1)
                    if (question != null) {
                        putInt(ARG_CORRECT_INDEX, question.correctQuestionIndex)
                    }
                    putString(ARG_TOPIC_NAME, topicName)
                }
            }

            Toast.makeText(requireContext(), "Moving on to the next question.", Toast.LENGTH_SHORT).show()

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack("QuizQuestionFragment") // Add a tag for this transaction
            transaction.commit()
        }

        finishBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Returning to the topic list.", Toast.LENGTH_SHORT).show()

            navigateToTopicListFragment()
        }

        return view
    }

    private fun navigateToTopicListFragment() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, TopicListFragment())
        transaction.addToBackStack(null) // Add the new fragment to the back stack
        transaction.commit()
    }
}