package edu.uw.ischool.dtsing.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    private lateinit var topicRepository: TopicRepository
    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp was created")

        // Initialize repository
        topicRepository = InMemoryTopicRepository()
    }

    // Method to retrieve the TopicRepository instance
    fun getTopicRepository(): TopicRepository {
        return topicRepository
    }
}