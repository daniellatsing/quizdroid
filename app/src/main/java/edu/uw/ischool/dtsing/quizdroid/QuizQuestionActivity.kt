package edu.uw.ischool.dtsing.quizdroid

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // Populate activity with intent
        // Get the Intent that started this activity and extract the string
        val numQuestion = intent.getIntExtra("NUM_QUESTION", 1)
        val numCorrect = intent.getIntExtra("NUM_CORRECT", 0)

        // Capture the layout's TextView and set the string as its text
        findViewById<TextView>(R.id.questionTextView).apply {text = numQuestion.toString()}
    }
}