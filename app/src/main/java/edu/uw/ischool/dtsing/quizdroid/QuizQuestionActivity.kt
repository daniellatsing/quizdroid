package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizQuestionActivity : AppCompatActivity() {
    private lateinit var backBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // Populate activity with intent
        // Get the Intent that started this activity and extract the string
        val numQuestion = intent.getIntExtra("NUM_QUESTION", 1)
        val numCorrect = intent.getIntExtra("NUM_CORRECT", 0)

        // Capture the layout's TextView and set the string as its text
        findViewById<TextView>(R.id.questionTextView).apply {
            text = numQuestion.toString()
        }

        backBtn = findViewById(R.id.backBtn)

        // Get reference to radio group options
        val radioGroupAnswers = findViewById<RadioGroup>(R.id.radioGroup)
        // Get reference to submit button
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        // If submit button is disabled, enable once the user selects an option
        radioGroupAnswers.setOnCheckedChangeListener { _, _ ->
            if (!submitBtn.isEnabled) {
                submitBtn.isEnabled = true
            }
        }

        // Start listening for next activity after clicking button
        submitBtn.setOnClickListener {
            val selectRB = radioGroupAnswers.checkedRadioButtonId
            val userAnswer = findViewById<RadioButton>(selectRB).text.toString()
            val intent = Intent(this, QuizAnswerActivity::class.java).apply {
                putExtra("USER_ANSWER", userAnswer)
                putExtra("NUM_QUESTION", numQuestion)
                putExtra("NUM_CORRECT", numCorrect)
            }

            startActivity(intent)
        }

        backBtn.setOnClickListener {
            if (numQuestion > 1) {
                val intent = Intent(this, QuizQuestionActivity::class.java).apply {
                    putExtra("NUM_QUESTION", numQuestion - 1)
                    putExtra("NUM_CORRECT", numCorrect) // Pass numCorrect to the next question
                }
                startActivity(intent)
            } else {
                // Let the user know they cannot go back further
                Toast.makeText(this, "You're already on the first question.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}