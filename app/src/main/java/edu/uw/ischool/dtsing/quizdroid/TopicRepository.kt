package edu.uw.ischool.dtsing.quizdroid

import java.io.File
import android.util.JsonReader
import java.io.FileReader

//interface TopicRepositoryDb {
//    fun getAllTopics(): List<Topic>
//    fun getTopic(name: String): Topic
//}

class TopicRepository (file: File) {

    // Part 3 - Read from local JSON file
    private val quizTopics: ArrayList<Topic> = readTopicsFromJson(file)

    private fun readTopicsFromJson(file: File): ArrayList<Topic> {
        val reader = JsonReader(FileReader(file))
        return try {
            readTopicsArray(reader)
        } finally {
            reader.close()
        }
    }

    private fun readTopicsArray(reader: JsonReader): ArrayList<Topic> {
        val topics = ArrayList<Topic>()

        reader.beginArray()
        while (reader.hasNext()) {
            topics.add(readTopic(reader))
        }
        reader.endArray()
        return topics
    }

    private fun readTopic(reader: JsonReader): Topic {
        var title: String = ""
        var desc: String = ""
        var questions = ArrayList<Question>()

        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            if (name.equals("title")) {
                title = reader.nextString()
            } else if (name.equals("desc")) {
                desc = reader.nextString()
            } else if (name.equals("questions")) {
                questions = readQuestions(reader)
            } else {
                reader.skipValue()
            }
        }
        reader.endObject()
        return Topic(title, desc, desc, questions)
    }

    private fun readQuestions(reader: JsonReader): ArrayList<Question> {
        val questions = ArrayList<Question>()

        reader.beginArray()
        while (reader.hasNext()) {
            var text: String = ""
            var answer: Int = -1
            var answers = ArrayList<String>()

            reader.beginObject()
            while (reader.hasNext()) {
                val name = reader.nextName()
                if (name.equals("text")) {
                    text = reader.nextString()
                } else if (name.equals("answer")) {
                    answer = reader.nextInt()
                } else if (name.equals("answers")) {
                    answers = readStringsArray(reader)
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject()
            questions.add(Question(text, answers, answer))
        }
        reader.endArray()
        return questions
    }

    private fun readStringsArray(reader: JsonReader): ArrayList<String> {
        val strings = ArrayList<String>()

        reader.beginArray()
        while (reader.hasNext()) {
            strings.add(reader.nextString())
        }
        reader.endArray()
        return strings
    }

    fun getAllTopics(): List<Topic> {
        return quizTopics
    }

    fun getTopic(name: String): Topic {
        return quizTopics.find {
            it.title == name
        } ?: throw IllegalArgumentException("Topic does not exist.")
    }
}

//    // Hard-coded list (Part 2)
//    private val topics = listOf(
//        // Math
//        Topic(
//            "Math",
//            "The science and study of quality, structure, space, and change.",
//            "The area of knowledge that includes the topics of numbers, formulas and " +
//                    "related structures, shapes, and the spaces in which they are contained, and " +
//                    "quantities and their changes.",
//            getMathQuestions()
//        ),
//        // Physics
//        Topic(
//            "Physics",
//            "The scientific study of the behavior of matter, energy, and force.",
//            "The branch of science that seeks to understand the fundamental principles " +
//                    "governing the behavior of matter, energy, space, and time.",
//            getPhysicsQuestions()
//        ),
//        // Biology
//        Topic(
//            "Biology",
//            "A branch of science study of life and living organisms.",
//            "The scientific study of life and living organisms, including their " +
//                    "structure, function, growth, evolution, distribution, and taxonomy.",
//            getBiologyQuestions()
//        ),
//        // Pokemon
//        Topic(
//            "Pokemon",
//            "A popular franchise that originated from a series of video games " +
//                    "developed by Nintendo.",
//            "Pokemon is a multimedia franchise centred around fictional creatures " +
//                    "called Pokemon. Trainers are able to catch and train these creatures for sport, " +
//                    "or keep them as pals.",
//            getPokemonQuestions()
//        ),
//        // League of Legends
//        Topic(
//            "League of Legends",
//            "A popular MOBA developed by Riot Games.",
//            "League of Legends (LoL) is a multiplayer online battle arena (MOBA) " +
//                    "game where players control powerful champions with unique abilities, working " +
//                    "together in teams to destroy the enemy's base while defending their own.",
//            getLeagueQuestions()
//        )
//    )

//    override fun getAllTopics(): List<Topic> {
//        return topics
//    }

//    override fun getTopic(name: String): Topic {
//        return topics.find{
//            it.title == name
//        } ?: throw IllegalArgumentException("Topic does not exist.")
//    }

//    private fun getMathQuestions() : List<Question> {
//        return listOf(
//            Question(
//                "What is 2 + 2?",
//                listOf("3", "4", "5", "6"),
//                1
//            ),
//            Question (
//                "Bill is ten years older than his sister. If Bill was twenty-five" +
//                        " years of age in 1983, in what year could he have been born?",
//                listOf("1948", "1953", "1958", "1963"),
//                3
//            ),
//            Question(
//                "What is the approximate value of the square root of 1596?",
//                listOf("10", "20", "30", "40"),
//                3
//            ),
//            Question(
//                "If x is a positive integer in the equation 12x = q, then q must be..?",
//                listOf(
//                    "a positive even integer",
//                    "a negative even integer",
//                    "a negative odd integer",
//                    "a positive odd integer"
//                ),
//                0
//            ),
//            Question(
//                "What does Lagrange's theorem state?",
//                listOf(
//                    "Every group is cyclic",
//                    "The order of a subgroup must divide the order of the group",
//                    "The order of a group must divide the order of its subgroup",
//                    "The order of a subgroup is always greater than the order of the group"
//                ),
//                1
//            )
//        )
//    }

//    private fun getPhysicsQuestions() : List<Question> {
//        return listOf(
//            Question(
//                "What is the SI unit of force?",
//                listOf("Joule", "Newton", "Watt", "Kilogram"),
//                1
//            ),
//            Question(
//                "Which of the following is a scalar quantity?",
//                listOf("Velocity", "Acceleration", "Force", "Distance"),
//                3
//            ),
//            Question(
//                "What is the law stating that an object at rest will remain at rest, " +
//                        "and an object in motion will remain in motion with constant velocity " +
//                        "unless acted upon by a net external force?",
//                listOf(
//                    "Newton's First Law of Motion",
//                    "Newton's Second Law of Motion",
//                    "Newton's Third Law of Motion",
//                    "Law of Universal Gravitation"
//                ),
//                0
//            ),
//            Question(
//                "What type of energy is associated with the motion of an object?",
//                listOf(
//                    "Potential energy",
//                    "Kinetic energy",
//                    "Thermal energy",
//                    "Chemical energy"
//                ),
//                1
//            ),
//            Question(
//                "What is the SI unit of Power?",
//                listOf("Watt", "Joule", "Newton", "Kilogram"),
//                0
//            )
//        )
//    }

//    private fun getBiologyQuestions() : List<Question> {
//        return listOf(
//            Question(
//                "What is the basic unit of life?",
//                listOf("Cell", "Atom", "Molecule", "Tissue"),
//                0
//            ),
//            Question(
//                "Which organelle is known as the powerhouse of the cell?",
//                listOf(
//                    "Nucleus",
//                    "Golgi Apparatus",
//                    "Mitochondrion",
//                    "Endoplasmic reticulum"
//                ),
//                2
//            ),
//            Question(
//                "During glycogenolysis, glycogen is broken down into:",
//                listOf(
//                    "Glucose-1-phosphate and ATP",
//                    "Glucose-6-phosphate and NADH",
//                    "Glucose-6-phosphate and ATP",
//                    "Glucose-1-phosphate and NADH"
//                ),
//                2
//            ),
//            Question(
//                "What is the process by which green plants make their food?",
//                listOf(
//                    "Respiration",
//                    "Photosynthesis",
//                    "Transpiration",
//                    "Fermentation"
//                ),
//                1
//            ),
//            Question(
//                "Which of the following is NOT a function of the cell membrane?",
//                listOf(
//                    "Regulating the passage of substances into and out of the cell",
//                    "Providing structural support to the cell",
//                    "Facilitating cell communication",
//                    "Synthesizing proteins"
//                ),
//                3
//            )
//        )
//    }

//    private fun getPokemonQuestions(): List<Question> {
//        return listOf(
//            Question(
//                "Which Pokemon type is super effective against Grass-type Pokemon?",
//                listOf("Fire", "Water", "Electric", "Flying"),
//                0
//            ),
//            Question(
//                "What is the evolved form of Pikachu?",
//                listOf("Raichu", "Pichu", "Jolteon", "Electabuzz"),
//                0
//            ),
//            Question(
//                "Which Pokemon is known as the 'Seed Pokemon'?",
//                listOf("Bulbasaur", "Chikorita", "Celebi", "Treecko"),
//                0
//            ),
//            Question(
//                "What type of Pokemon is Charizard?",
//                listOf("Fire/Flying", "Fire/Dragon", "Fire", "Dragon/Flying"),
//                0
//            ),
//            Question(
//                "Which Pokemon is known as the 'Water-type starter Pokemon' in the " +
//                        "Kanto region?",
//                listOf("Squirtle", "Totodile", "Mudkip", "Piplup"),
//                0
//            ),
//        )
//    }

//    private fun getLeagueQuestions(): List<Question> {
//        return listOf(
//            Question(
//                "What is the name of the main map used in most League of " +
//                        "Legends matches?",
//                listOf(
//                    "Summoner's Valley",
//                    "Rift Valley",
//                    "Summoner's Rift",
//                    "The Howling Abyss"
//                ),
//                2
//            ),
//            Question(
//                "Which of the following is not a primary resource used by champions " +
//                        "in League of Legends?",
//                listOf("Mana", "Energy", "Fury", "Health"),
//                3
//            ),
//            Question(
//                "Who is the ruler of Demacia in the lore of League of Legends?",
//                listOf("Jarvan III", "Garen Crownguard", "Luxanna Crownguard", "Sylas"),
//                0
//            ),
//            Question(
//                "Which champion was a member of the Kinkou Order before breaking " +
//                        "away to pursue their own path?",
//                listOf("Akali", "Shen", "Zed", "Kennen"),
//                2
//            ),
//            Question(
//                "Which champion is associated with the Shadow Isles and seeks to " +
//                        "undo the curse that afflicts their homeland?",
//                listOf("Thresh", "Kalista", "Yorick", "Maokai"),
//                2
//            ),
//        )
//    }
// }