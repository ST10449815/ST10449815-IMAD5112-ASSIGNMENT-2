package com.example.imad5112_assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Retrieve score and answers from the quiz activity
    val score = intent.getIntExtra("SCORE", 0)
    val answers = intent.getBooleanArrayExtra("ANSWERS")

    val scoreText = findViewById<TextView>(R.id.score_text)
        //Feedback provided for the users based on their scores
    val feedback = when {
        score == 5 -> "Excellent!"
        score >= 3 -> "Good job!"
        else -> "Keep practicing!"
    }
        // Display the score and feedback
     scoreText.text = "You got $score/5 correct.\n$feedback"

    findViewById<Button>(R.id.review_button).setOnClickListener {
        val intent = Intent(this, MainActivity4::class.java)
        intent.putExtra("ANSWERS", answers)
        startActivity(intent)
    }
    //Exit button to exit the application completely
    findViewById<Button>(R.id.exit_button).setOnClickListener {
        finishAffinity()
    }
 }
}