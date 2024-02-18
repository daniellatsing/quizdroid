package edu.uw.ischool.dtsing.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    lateinit var topicRepository: TopicRepositoryDb

    companion object {
        lateinit var instance: QuizApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d("QuizApp", "QuizApp was created")

        // Initialize repository
        topicRepository = TopicRepository()
    }
}