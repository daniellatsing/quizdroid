package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // Setup variables for later initialization
    private lateinit var rvList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference RecyclerView
        rvList = findViewById(R.id.rv_list)

        // Hard-coded list of sample topic names
        val data = listOf(
            "Math",
            "Physics",
            "Biology",
            "Pokemon Types",
            "League of Legends"
        )

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = RVAdapter(data, this)
    }

    fun onItemClick(item: String) {
        // Add clicked topic name as extra
        val intent = Intent(this, TopicOverviewActivity::class.java).apply {
            putExtra("SELECTED_TOPIC", item)
        }
        startActivity(intent)

        Toast.makeText(this, "You clicked on ${item}!", Toast.LENGTH_SHORT).show()
    }

}