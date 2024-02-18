package edu.uw.ischool.dtsing.quizdroid

data class Question(
    var questionText: String,
    val answers: List<String>,
    val correctQuestionIndex: Int
)