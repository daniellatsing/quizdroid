package edu.uw.ischool.dtsing.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rv_list)

        val data = listOf(
            "Math",
            "Physics",
            "Marvel Super Heroes",
            "Pokemon Types",
            "League of Legends"
        )

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = RVAdapter(data, this)
    }

    fun onItemClick(item: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        startActivity(intent)

        Toast.makeText(this, "You clicked on ${item}!", Toast.LENGTH_SHORT).show()
    }

}