package edu.uw.ischool.dtsing.quizdroid

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.core.os.bundleOf

interface TopicRepositoryDb {
    fun getAllTopics(): List<Topic>
    fun getTopic(topic: Topic): Topic?
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
                    "What is 2 + 2?",
                    listOf("3", "4", "5", "6"),
                    2
                ),
                Question (
                    "Bill is ten yeras older than his sister. If Bill was twenty-five" +
                            " years of age in 1983, in what year could he have been born?",
                    listOf("1948", "1953", "1958", "1963"),
                    3
                ),
                Question(
                    "What is the approximate value of the square root of 1596?",
                    listOf("10", "20", "30", "40"),
                    4
                ),
                Question(
                    "If x is a positive integer in the equation 12x = q, then q must be..?",
                    listOf("a positive even integer", "a negative even integer", "zero",
                        "a positive odd integer", "a negative odd integer"),
                    1
                ),
                Question(
                    "What does Lagrange's theorem state?",
                    listOf(
                        "Every group is cyclic",
                        "The order of a subgroup must divide the order of the group",
                        "The order of a group must divide the order of its subgroup",
                        "The order of a subgroup is always greater than the order of the group"
                    ),
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
                    "What is the SI unit of force?",
                    listOf("Joule", "Newton", "Watt", "Kilogram"),
                    2
                ),
                Question(
                    "Which of the following is a scalar quantity?",
                    listOf("Velocity", "Acceleration", "Force", "Distance"),
                    4
                ),
                Question(
                    "What is the law stating that an object at rest will remain at rest," +
                            "and an object in omtion will remain in motion with constant velocity" +
                            "unless acted upon by a net external force?",
                    listOf(
                        "Newton's First Law of Motion",
                        "Newton's Second Law of Motion",
                        "Newton's Third Law of Motion",
                        "Law of Universal Gravitation"
                    ),
                    1
                ),
                Question(
                   "What type of energy is associated with the motion of an object?",
                    listOf("Potential energy", "Kinetic energy", "Thermal energy", "Chemical energy"),
                    2
                ),
                Question(
                    "What is the SI unit of Power?",
                    listOf("Watt", "Joule", "Newton", "Kilogram"),
                    1
                )
            )
        ),
        // Biology
        Topic(
            "Biology",
            "",
            "",
            listOf(
                Question(
                    "What is the basic unit of life?",
                    listOf("Cell", "Atom", "Molecule", "Tissue"),
                    1
                ),
                Question(
                    "Which organelle is known as the powerhouse of the cell?",
                    listOf("Nucleus", "Golgi Apparatus", "Mitochondrion", "Endoplasmic reticulum"),
                    3
                ),
                Question("During glycogenolysis, glycogen is broken down into:",
                    listOf(
                        "Glucose-1-phosphate and ATP",
                        "Glucose-6-phosphate and NADH",
                        "Glucose-6-phosphate and ATP",
                        "Glucose-1-phosphate and NADH"
                    ),
                    3
                ),
                Question(
                    "What is the process by which green plants make their food?",
                    listOf("Respiration", "Photosynthesis", "Transpiration", "Fermentation"),
                    2
                ),
                Question(
                    "Which of the following is NOT a function of the cell membrane?",
                    listOf(
                        "Regulating the passage of substances into and out of the cell",
                        "Providing structural support to the cell",
                        "Facilitating cell communication",
                        "Synthesizing proteins"
                    ),
                    4
                )
            )
        ),
        // Pokemon
        Topic(
            "Pokemon",
            "",
            "",
            listOf(
                Question(
                    "Which Pokemon type is super effective against Grass-type Pokemon?",
                    listOf("Fire", "Water", "Electric", "Flying"),
                    1
                ),
                Question(
                    "What is the evolved form of Pikachu?",
                    listOf("Raichu", "Pichu", "Jolteon", "Electabuzz"),
                    1
                ),
                Question(
                    "Which Pokemon is known as the 'Seed Pokemon'?",
                    listOf("Bulbasaur", "Chikorita", "Celebi", "Treecko"),
                    1
                ),
                Question(
                    "What type of Pokemon is Charizard?",
                    listOf("Fire/Flying", "Fire/Dragon", "Fire", "Dragon/Flying"),
                    1
                ),
                Question(
                    "Which Pokemon is known as the 'Water-type starter Pokemon' in the" +
                            "Kanto region?",
                    listOf("Squirtle", "Totodile", "Mudkip", "Piplup"),
                    1
                ),
            )
        ),
        // League of Legends
        Topic(
            "League of Legends",
            "",
            "",
            listOf(
                Question(
                    "What is the name of the main map used in most League of " +
                            "Legends matches?",
                    listOf("Summoner's Valley", "Rift Valley", "Summoner's Rift", "The Howling Abyss"),
                    3
                ),
                Question(
                    "Which of the following is not a primary resource used by champions" +
                            "in League of Legends?",
                    listOf("Mana", "Energy", "Fury", "Health"),
                    4
                ),
                Question(
                    "Who is the ruler of Demacia in the lore of League of Legends?",
                    listOf("Jarvan III", "Garen Crownguard", "Luxanna Crownguard", "Sylas"),
                    1
                ),
                Question(
                    "Which champon was a member of the Kinkou Order before breaking" +
                            "away to pursue their own path?",
                    listOf("Akali", "Shen", "Zed", "Kennen"),
                    3
                ),
                Question(
                    "Which champion is associated with the Shadow Isles and seeks to" +
                            "undo the curse that afflicts their homeland?",
                    listOf("Thresh", "Kalista", "Yorick", "Maokai"),
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
}

data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questionsList: List<Question>
)

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswer: Int
)

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

        // Load the initial fragment
        if (savedInstanceState == null) {
            val mainFragment = MainFragment.newInstance(topics)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment)
                .commit()
        }
    }

    // Function to navigate to QuizQuestionFragment
    fun navToQuizQuestion(numQuestion: Int, numCorrect: Int, topic:String) {
        val fragment = QuizQuestionFragment().apply {
            arguments = bundleOf(
                "NUM_QUESTION" to numQuestion,
                "NUM_CORRECT" to numCorrect,
                "TOPIC" to topic)
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
    fun navToTopicOverview(topic: String) {
        val fragment = TopicOverviewFragment.newInstance(topic)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
