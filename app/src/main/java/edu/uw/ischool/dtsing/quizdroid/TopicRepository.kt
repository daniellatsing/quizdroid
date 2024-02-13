package edu.uw.ischool.dtsing.quizdroid

interface TopicRepository {
    fun getAllTopics(): List<String>
}

class InMemoryTopicRepository : TopicRepository {
    // Hard-coded list
    private val topics = listOf(
        "Math",
        "Physics",
        "Biology",
        "Pokemon Types",
        "League of Legends"
    )

    override fun getAllTopics(): List<String> {
        return topics
    }
}