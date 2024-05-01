package com.example.program_transfer_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class quiz_result : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().getReference("Users")

        // Retrieve data from intent extras
        val userName = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val score = intent.getIntExtra(Constants.SCORE, 0)

        // Find views
        val congratulationsTv: TextView = findViewById(R.id.congratulationsTv)
        val scoreTv: TextView = findViewById(R.id.scoreTv)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        // Display congratulations message and score
        congratulationsTv.text = "Congratulations, $userName!"
        scoreTv.text = "Your score is $score of $totalQuestions"

        // Save score to Firebase Realtime Database
        saveScoreToDatabase(userName, score)

        // Restart quiz button click listener
        btnRestart.setOnClickListener {
            val intent = Intent(this, quizmain::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun saveScoreToDatabase(userName: String?, score: Int) {
        if (userName != null) {
            // Create a new entry in the database under "Users" node with user's name as key
            database.child(userName).setValue(score)
                .addOnSuccessListener {
                    Toast.makeText(this, "Score Saved Successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to Save Score", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
