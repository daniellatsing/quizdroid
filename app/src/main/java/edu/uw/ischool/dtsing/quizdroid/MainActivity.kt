package edu.uw.ischool.dtsing.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment())
                .commit()
        }
    }

    // Function to navigate to QuizQuestionFragment
    fun navToQuizQuestion(numQuestion: Int, numCorrect: Int) {
        val fragment = QuizQuestionFragment().apply {
            arguments = bundleOf("NUM_QUESTION" to numQuestion, "NUM_CORRECT" to numCorrect)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Function to navigate to QuizAnswerFragment
    fun navToQuizAnswer(userAnswer:String, numQuestion: Int, numCorrect: Int) {
        val fragment = QuizAnswerFragment().apply {
            arguments = bundleOf("USER_ANSWER" to userAnswer, "NUM_QUESTION" to numQuestion, "NUM_CORRECT" to numCorrect)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Function to navigate to TopicOverviewFragment
    fun navToTopicOverview(topic: String) {
        val fragment = TopicOverviewFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
