package edu.uw.ischool.dtsing.quizdroid

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf

interface TopicRepositoryDb {
    fun getAllTopics(): List<Topic>
    fun getTopic(topic: Topic): Topic?
    fun getQuestionsForTopic(topic: Topic): List<Question>
}

class TopicRepository : TopicRepositoryDb {
    // Hard-coded list
    private val topics = listOf(
        // Math
        Topic(
            "Math",
            "The science and study of quality, structure, space, and change.",
            "The area of knowledge that includes the topics of numbers, formulas and " +
                    "related structures, shapes, and the spaces in which they are contained, and " +
                    "quantities and their changes.",
            listOf(
                Question(
                    1,
                    "What is 2 + 2?",
                    "3",
                    "4",
                    "5",
                    "6",
                    2
                ),
                Question (
                    2,
                    "Bill is ten years older than his sister. If Bill was twenty-five" +
                            " years of age in 1983, in what year could he have been born?",
                    "1948",
                    "1953",
                    "1958",
                    "1963",
                    3
                ),
                Question(
                    3,
                    "What is the approximate value of the square root of 1596?",
                    "10",
                    "20",
                    "30",
                    "40",
                    4
                ),
                Question(
                    4,
                    "If x is a positive integer in the equation 12x = q, then q must be..?",
                    "a positive even integer",
                    "a negative even integer",
                    "a negative odd integer",
                    "a positive odd integer",
                    1
                ),
                Question(
                    5,
                    "What does Lagrange's theorem state?",
                    "Every group is cyclic",
                    "The order of a subgroup must divide the order of the group",
                    "The order of a group must divide the order of its subgroup",
                    "The order of a subgroup is always greater than the order of the group"
                    ,
                    2
                )
            )
        ),
        // Physics
        Topic(
            "Physics",
            "The scientific study of the behavior of matter, energy, and force",
            "The branch of science that seeks to understand the fundamental principles" +
                    "governing the behavior of matter, energy, space, and time",
            listOf(
                Question(
                    1,
                    "What is the SI unit of force?",
                    "Joule",
                    "Newton",
                    "Watt",
                    "Kilogram",
                    2
                ),
                Question(
                    2,
                    "Which of the following is a scalar quantity?",
                    "Velocity",
                    "Acceleration",
                    "Force",
                    "Distance",
                    4
                ),
                Question(
                    3,
                    "What is the law stating that an object at rest will remain at rest," +
                            "and an object in motion will remain in motion with constant velocity" +
                            "unless acted upon by a net external force?",
                    "Newton's First Law of Motion",
                    "Newton's Second Law of Motion",
                    "Newton's Third Law of Motion",
                    "Law of Universal Gravitation",
                    1
                ),
                Question(
                    4,
                   "What type of energy is associated with the motion of an object?",
                    "Potential energy",
                    "Kinetic energy",
                    "Thermal energy",
                    "Chemical energy",
                    2
                ),
                Question(
                    5,
                    "What is the SI unit of Power?",
                    "Watt",
                    "Joule",
                    "Newton",
                    "Kilogram",
                    1
                )
            )
        ),
        // Biology
        Topic(
            "Biology",
            "A branch of science study of life and living organisms.",
            "The scientific study of life and living organisms, including their " +
                    "structure, function, growth, evolution, distribution, and taxonomy.",
            listOf(
                Question(
                    1,
                    "What is the basic unit of life?",
                    "Cell",
                    "Atom",
                    "Molecule",
                    "Tissue",
                    1
                ),
                Question(
                    2,
                    "Which organelle is known as the powerhouse of the cell?",
                    "Nucleus",
                    "Golgi Apparatus",
                    "Mitochondrion",
                    "Endoplasmic reticulum",
                    3
                ),
                Question(
                    3,"During glycogenolysis, glycogen is broken down into:",
                    "Glucose-1-phosphate and ATP",
                    "Glucose-6-phosphate and NADH",
                    "Glucose-6-phosphate and ATP",
                    "Glucose-1-phosphate and NADH",
                    3
                ),
                Question(
                    4,
                    "What is the process by which green plants make their food?",
                    "Respiration",
                    "Photosynthesis",
                    "Transpiration",
                    "Fermentation",
                    2
                ),
                Question(
                    5,
                    "Which of the following is NOT a function of the cell membrane?",
                    "Regulating the passage of substances into and out of the cell",
                    "Providing structural support to the cell",
                    "Facilitating cell communication",
                    "Synthesizing proteins",
                    4
                )
            )
        ),
        // Pokemon
        Topic(
            "Pokemon",
            "A popular franchise that originated from a series of video games " +
                    "developed by Nintendo.",
            "Pokemon is a multimedia franchise centred around fictional creatures" +
                    "called Pokemon. Trainers are able to catch and train these creatures for sport," +
                    "or keep them as pals.",
            listOf(
                Question(
                    1,
                    "Which Pokemon type is super effective against Grass-type Pokemon?",
                    "Fire", "Water", "Electric", "Flying",
                    1
                ),
                Question(
                    2,
                    "What is the evolved form of Pikachu?",
                    "Raichu", "Pichu", "Jolteon", "Electabuzz",
                    1
                ),
                Question(
                    3,
                    "Which Pokemon is known as the 'Seed Pokemon'?",
                    "Bulbasaur", "Chikorita", "Celebi", "Treecko",
                    1
                ),
                Question(
                    4,
                    "What type of Pokemon is Charizard?",
                    "Fire/Flying", "Fire/Dragon", "Fire", "Dragon/Flying",
                    1
                ),
                Question(
                    5,
                    "Which Pokemon is known as the 'Water-type starter Pokemon' in the" +
                            "Kanto region?",
                    "Squirtle", "Totodile", "Mudkip", "Piplup",
                    1
                ),
            )
        ),
        // League of Legends
        Topic(
            "League of Legends",
            "A popular MOBA developed by Riot Games.",
            "League of Legends (LoL) is a multiplayer online battle arena (MOBA)" +
                    "game where players control powerful champions with unique abilities, working" +
                    "together in teams to destroy the enemy's base while defending their own.",
            listOf(
                Question(
                    1,
                    "What is the name of the main map used in most League of " +
                            "Legends matches?",
                    "Summoner's Valley",
                    "Rift Valley",
                    "Summoner's Rift",
                    "The Howling Abyss",
                    3
                ),
                Question(
                    2,
                    "Which of the following is not a primary resource used by champions" +
                            "in League of Legends?",
                    "Mana",
                    "Energy",
                    "Fury",
                    "Health",
                    4
                ),
                Question(
                    3,
                    "Who is the ruler of Demacia in the lore of League of Legends?",
                    "Jarvan III",
                    "Garen Crownguard",
                    "Luxanna Crownguard",
                    "Sylas",
                    1
                ),
                Question(
                    4,
                    "Which champon was a member of the Kinkou Order before breaking" +
                            "away to pursue their own path?",
                    "Akali",
                    "Shen",
                    "Zed",
                    "Kennen",
                    3
                ),
                Question(
                    5,
                    "Which champion is associated with the Shadow Isles and seeks to" +
                            "undo the curse that afflicts their homeland?",
                    "Thresh",
                    "Kalista",
                    "Yorick",
                    "Maokai",
                    3
                ),
            )
        )
    )

    override fun getAllTopics(): List<Topic> {
        return topics
    }

    override fun getTopic(topic: Topic): Topic? {
        for (t in topics) {
            if (t == topic) {
                return t
            }
        }
        return null
    }

    override fun getQuestionsForTopic(topic: Topic): List<Question> {
        val foundTopic = topics.find { it == topic }
        return foundTopic?.questionsList ?: emptyList()
    }
}

data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questionsList: List<Question>
)

data class Question(
    val num : Int,
    val questionText: String,
    val a1: String,
    val a2: String,
    val a3: String,
    val a4: String,
    val correctAnswer: Int
) {
    val questionNum = num
}

class QuizApp : Application() {
    private lateinit var topicRepository: TopicRepositoryDb
    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp was created")

        // Initialize repository
        topicRepository = TopicRepository()
    }

    // Method to retrieve the TopicRepository instance
    fun getRepository(): TopicRepository {
        return topicRepository as TopicRepository
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizApp = (application as QuizApp)
        val repo = quizApp.getRepository()
        val topics = repo.getAllTopics()
        val topic = repo.getTopic(topics.first())
        val shortDesc = topic?.shortDescription.toString()
        val longDesc = topic?.longDescription.toString()

        // Load the initial fragment
        if (savedInstanceState == null) {
            val mainFragment = MainFragment.newInstance(topics)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment)
                .commit()
        }
    }

    // Function to navigate to QuizQuestionFragment
    fun navToQuizQuestion(numQuestion: Int, numCorrect: Int, question:String) {
        val fragment = QuizQuestionFragment().apply {
            arguments = bundleOf(
                "NUM_QUESTION" to numQuestion,
                "NUM_CORRECT" to numCorrect,
                "QUESTION" to question)
        }
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
