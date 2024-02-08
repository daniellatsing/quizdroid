package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverviewActivity : AppCompatActivity() {
    private lateinit var backBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        // Populate activity with intent
        // Get the Intent that started this activity and extract the string
        val topicName = intent.getStringExtra("SELECTED_TOPIC")

        // Capture the layout's TextView and set the string as its text
        findViewById<TextView>(R.id.topicTextView).apply {
            text = topicName
        }

        backBtn = findViewById(R.id.backBtn)

        // Get reference to the next activity
        // Get reference to begin button
        val beginBtn = findViewById<Button>(R.id.beginQuizBtn)

        // Start listening for events when button is clicked
        beginBtn.setOnClickListener {
            val intent = Intent(this, QuizQuestionActivity::class.java).apply {
                putExtra("NUM_QUESTION", 1)
            }

            startActivity(intent)
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    
}