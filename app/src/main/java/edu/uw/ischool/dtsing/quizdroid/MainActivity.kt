package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, TopicListFragment())
                .commit()
        }
    }

    // Function to navigate to QuizQuestionFragment
    fun navToQuizQuestion(topic: String, currentQuestionNumber: Int) {
        val fragment = QuizQuestionFragment.newInstance(topic, currentQuestionNumber)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Function to navigate to QuizAnswerFragment
    fun navToQuizAnswer(userAnswer:String, numQuestion: Int, numCorrect: Int) {
        val fragment = QuizAnswerFragment().apply {
            arguments = bundleOf(
                "USER_ANSWER" to userAnswer,
                "NUM_QUESTION" to numQuestion,
                "NUM_CORRECT" to numCorrect
            )
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Function to navigate to TopicOverviewFragment
    fun navToTopicOverview(topic: String, longDescription: String) {
        val fragment = TopicOverviewFragment.newInstance(topic, longDescription)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
