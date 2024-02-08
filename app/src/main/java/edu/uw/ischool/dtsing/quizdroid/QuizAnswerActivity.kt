package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizAnswerActivity : AppCompatActivity() {
    private lateinit var nextBtn: Button
    private lateinit var finishBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_answer)

        // Populate activity with intent
        // Get the Intent that started this activity and extract the string
        val numQuestion = intent.getIntExtra("NUM_QUESTION", 1)
        var numCorrect = intent.getIntExtra("NUM_CORRECT", 0)
        val userAnswer = intent.getStringExtra("USER_ANSWER")

        // Differentiate between next and finished to decide what button will show up
        val quizStatus = if (numQuestion < 5) {
            "Next"
        } else {
            "Finished"
        }

        // Capture the user's answer TextView and set the string as its text
        findViewById<TextView>(R.id.userAnswer).apply {
            text = userAnswer
        }

        // Compare user answer to correct answer
        val correctAnswer = "Option 1" // Assuming the correct answer is always "Option 1"
        if (userAnswer == correctAnswer) {
            numCorrect++
        }


        // Capture reference to the next and finish buttons and set its text
        nextBtn = findViewById<Button>(R.id.nextBtn).apply {
            text = quizStatus
        }

        finishBtn = findViewById<Button>(R.id.finishBtn).apply {
            text = quizStatus
        }

        updateButtonVisibility(numQuestion)

        // Capture reference to the quiz number and set the value as its text
        val quizScoreTextView = findViewById<TextView>(R.id.quizScore)
        quizScoreTextView.text = getString(R.string.quiz_score, numCorrect)


        // Start listening for next activity after clicking button
        nextBtn.setOnClickListener {
            val intent = Intent(this, QuizQuestionActivity::class.java).apply {
                putExtra("NUM_QUESTION", numQuestion + 1)
                putExtra("NUM_CORRECT", numCorrect)
            }
            startActivity(intent)
        }

        finishBtn.setOnClickListener {
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
        }
    }

    private fun updateButtonVisibility(numQuestion : Int) {
        if(numQuestion == 5) {
            nextBtn.visibility = View.GONE
            finishBtn.visibility = View.VISIBLE

        } else {
            nextBtn.visibility = View.VISIBLE
            finishBtn.visibility = View.GONE
        }
    }
}