package edu.uw.ischool.dtsing.quizdroid

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

private const val ARG_TOPIC = "topic"
private const val ARG_CURRENT_QUESTION = "currentQuestionNumber"

class QuizQuestionFragment : Fragment() {
    private var topic: String? = null
    private var currentQuestionNumber: Int? = null
    private lateinit var topicRepository: TopicRepository
    private var topicObject: Topic? = null
    private lateinit var submitBtn: Button
    private lateinit var backBtn: Button
    private lateinit var radioGroup: RadioGroup

    companion object {
        fun newInstance(topic: String, currentQuestionNumber: Int): QuizQuestionFragment {
            val fragment = QuizQuestionFragment()
            val args = Bundle().apply {
                putString(ARG_TOPIC, topic)
                putInt(ARG_CURRENT_QUESTION, currentQuestionNumber)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicRepository = TopicRepository()

        // Retrieve topic and description from arguments
        arguments?.let {
            topic = it.getString(ARG_TOPIC)
            currentQuestionNumber = it.getInt(ARG_CURRENT_QUESTION)
            if (topic != null) {
                topicObject = (activity?.application as? QuizApp)?.topicRepository?.getTopic(topic!!)
            } else {
                Toast.makeText(requireContext(), "Topic name is null", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Topic name is null")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_quiz_question, container, false)

        radioGroup = view.findViewById(R.id.radioGroup)

        val question = topicObject?.questionsList?.getOrNull(currentQuestionNumber!!)

        // If submit button is disabled, enable once the user selects an option
        radioGroup.setOnCheckedChangeListener { _, _ ->
            if (!submitBtn.isEnabled) {
                submitBtn.isEnabled = true
            }
        }

        // Get reference to buttons
        backBtn = view.findViewById(R.id.backBtn)
        submitBtn = view.findViewById(R.id.submitBtn)

        // When the user submits an answer
        submitBtn.setOnClickListener {
            val selectRB = radioGroup.checkedRadioButtonId
            val userAnswer = view.findViewById<RadioButton>(selectRB)?.text?.toString()
            if (userAnswer != null) {
                if (question != null) {
                    val mainActivity = requireActivity() as? MainActivity
                    mainActivity?.navToQuizAnswer(
                        userAnswer,
                        currentQuestionNumber!!,
                        question.correctQuestionIndex
                    )
                }
            } else {
                Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Set click listener for the back button
        backBtn.setOnClickListener {
            if (currentQuestionNumber!! > 1) {
                if (question != null) {
                    val mainActivity = requireActivity() as? MainActivity
                    mainActivity?.navToQuizQuestion(
                        topic!!,
                        currentQuestionNumber!! - 1
                    )
                }
            } else {
                // Let the user know they cannot go back further
                Toast.makeText(
                    requireContext(),
                    "There are no more previous questions.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve topic and description from arguments
        arguments?.let {
            topic = it.getString(ARG_TOPIC)
            currentQuestionNumber = it.getInt(ARG_CURRENT_QUESTION)
        }

        val quizApp = activity?.application as QuizApp
        topic?.let { topicName ->
            topicObject = quizApp.topicRepository.getTopic(topicName)
        }
        val question = topicObject?.questionsList?.get(currentQuestionNumber!!)
        if (question != null) {
            getQAViews(question)
        } else {
            Toast.makeText(requireContext(), "Failed to retrieve question", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Failed to retrieve question: question is null")
        }
    }

    private fun getQAViews(question: Question) {
        view?.findViewById<TextView>(R.id.questionTextView)?.text = question.questionText

        view?.findViewById<RadioButton>(R.id.radioButton)?.text = question.answers[0]
        view?.findViewById<RadioButton>(R.id.radioButton2)?.text = question.answers[1]
        view?.findViewById<RadioButton>(R.id.radioButton3)?.text = question.answers[2]
        view?.findViewById<RadioButton>(R.id.radioButton4)?.text = question.answers[3]
    }
}