package edu.uw.ischool.dtsing.quizdroid

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
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class QuizQuestionFragment : Fragment() {
    private lateinit var submitBtn: Button
    private lateinit var backBtn: Button
    private lateinit var radioGroupAnswers: RadioGroup
    private var questionList: List<Question>? = null
    private var topics : List<Topic>? = null
    private var currentQuestionNumber: Int = 0
    private var numCorrect : Int = 0

    private fun handleOptionClick(selectedOption: Int) {

        // Check if questionList is not null
        val questionList = questionList ?: return

        // Ensure currentQuestionNumber is valid
        if (currentQuestionNumber < 0 || currentQuestionNumber >= questionList.size) {
            // Invalid current question number
            return
        }

        // Get the current question
        val currentQuestion = questionList[currentQuestionNumber]
        // Check if the selected option matches the correct answer
        if (selectedOption == currentQuestion.correctAnswer) {
            numCorrect++

        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_question, container, false)

        arguments?.let { args ->
            questionList = args.getParcelableArrayList("QUESTION_LIST")
            currentQuestionNumber = args.getInt("CURRENT_QUESTION_NUMBER", 0)
        }

        // Display current question
        questionList?.let { questions ->
            val currentQuestion = questions[currentQuestionNumber]
            val questionTextView = view.findViewById<TextView>(R.id.questionTextView)
            val option1TextView = view.findViewById<TextView>(R.id.radioButton)
            val option2TextView = view.findViewById<TextView>(R.id.radioButton2)
            val option3TextView = view.findViewById<TextView>(R.id.radioButton3)
            val option4TextView = view.findViewById<TextView>(R.id.radioButton4)

            questionTextView.text = currentQuestion.questionText
            option1TextView.text = currentQuestion.a1
            option2TextView.text = currentQuestion.a2
            option3TextView.text = currentQuestion.a3
            option4TextView.text = currentQuestion.a4

            // Set click listeners for options
            option1TextView.setOnClickListener { handleOptionClick(1) }
            option2TextView.setOnClickListener { handleOptionClick(2) }
            option3TextView.setOnClickListener { handleOptionClick(3) }
            option4TextView.setOnClickListener { handleOptionClick(4) }
        }

        // Define the onBackPressedCallback outside of the lambda
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Check if it's the first question page
                if (currentQuestionNumber == 1) {
                    // Navigate back to the topic list page
                    (requireActivity() as MainActivity).navToTopicOverview(
                        topics.title,
                        topics.longDescription
                    )
                } else {
                    // Navigate to the previous question fragment
                    (requireActivity() as MainActivity).navToQuizQuestion(
                        currentQuestionNumber - 1,
                        numCorrect,
                        topic
                    )
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        // Get reference to buttons
        backBtn = view.findViewById(R.id.backBtn)
        submitBtn = view.findViewById(R.id.submitBtn)

        // If submit button is disabled, enable once the user selects an option
        radioGroupAnswers.setOnCheckedChangeListener { _, _ ->
            if (!submitBtn.isEnabled) {
                submitBtn.isEnabled = true
            }
        }

        // Start listening for next activity after clicking button
        submitBtn.setOnClickListener {
            val selectRB = radioGroupAnswers.checkedRadioButtonId
            val userAnswer = view.findViewById<RadioButton>(selectRB)?.text?.toString()
            if (userAnswer != null) {
                // Navigate to QuizAnswerFragment with user's answer
                (requireActivity() as MainActivity).navToQuizAnswer(
                    userAnswer,
                    currentQuestionNumber,
                    numCorrect
                )
            } else {
                Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Set click listener for the back button
        backBtn.setOnClickListener {
            if (currentQuestionNumber > 1) {
                // Navigate to the previous question fragment
                (requireActivity() as MainActivity).navToQuizQuestion(
                    currentQuestionNumber - 1,
                    numCorrect,
                    topic
                )
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
}