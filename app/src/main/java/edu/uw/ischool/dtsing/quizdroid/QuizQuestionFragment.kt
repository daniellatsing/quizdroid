package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class QuizQuestionFragment : Fragment() {
    private lateinit var submitBtn: Button
    private lateinit var backBtn: Button
    private lateinit var radioGroupAnswers: RadioGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_question, container, false)

        // Get the question number and number of correct answers from arguments
        val numQuestion = requireArguments().getInt("NUM_QUESTION", 1)
        val numCorrect = requireArguments().getInt("NUM_CORRECT", 0)

        // Capture the layout's TextView and set the question number as its text
        view.findViewById<TextView>(R.id.questionTextView).text = numQuestion.toString()

        // Find the back button in the layout
        backBtn = view.findViewById(R.id.backBtn)

        // Get reference to radio group options
        radioGroupAnswers = view.findViewById(R.id.radioGroup)

        // Get reference to submit button
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
                (requireActivity() as MainActivity).navToQuizAnswer(userAnswer, numQuestion, numCorrect)
            } else {
                Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for the back button
        backBtn.setOnClickListener {
            if (numQuestion > 1) {
                // Navigate to the previous question fragment
                (requireActivity() as MainActivity).navToQuizQuestion(numQuestion - 1, numCorrect)
            } else {
                // Let the user know they cannot go back further
                Toast.makeText(requireContext(), "There are no more previous questions.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}