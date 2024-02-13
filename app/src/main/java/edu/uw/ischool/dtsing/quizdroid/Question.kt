package edu.uw.ischool.dtsing.quizdroid

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerNum: Int
)
