package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuizAnswerFragment : Fragment() {
    private lateinit var nextBtn: Button
    private lateinit var finishBtn: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_answer, container, false)

        // Get data from arguments
        val numQuestion = requireArguments().getInt("NUM_QUESTION", 1)
        var numCorrect = requireArguments().getInt("NUM_CORRECT", 0)
        val userAnswer = requireArguments().getString("USER_ANSWER") ?: ""

        // Differentiate betwen next and finished to decide what button will show up
        val quizStatus = if (numQuestion < 5) {
            "Next"
        } else {
            "Finished"
        }

        // Set user's answer TextView
        view.findViewById<TextView>(R.id.userAnswer).text = userAnswer

        // Compare user answer to correct answer
        val correctAnswer = "Option 1" // Assuming the correct answer is always "Option 1"
        if (userAnswer == correctAnswer) {
            numCorrect++
        }

        // Set quiz score text
        view.findViewById<TextView>(R.id.quizScore).text = getString(R.string.quiz_score, numCorrect)

        // Capture reference to the next and finish buttons
        nextBtn = view.findViewById<Button>(R.id.nextBtn).apply {
            text = quizStatus
            visibility = if (numQuestion < 5) View.VISIBLE else View.GONE
        }

        finishBtn = view.findViewById<Button>(R.id.finishBtn).apply {
            text = quizStatus
            visibility = if (numQuestion == 5) View.VISIBLE else View.GONE
        }

        // Start listening for next fragment after clicking button
        nextBtn.setOnClickListener {
            val fragment = QuizQuestionFragment().apply {
                arguments = Bundle().apply {
                    putInt("NUM_QUESTION", numQuestion + 1)
                    putInt("NUM_CORRECT", numCorrect)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }


        // TODO: Fix going back to Main Screen once finishing quiz
        finishBtn.setOnClickListener {
            // Go back to MainActivity
            requireActivity().finish()
        }

        return view
    }
}