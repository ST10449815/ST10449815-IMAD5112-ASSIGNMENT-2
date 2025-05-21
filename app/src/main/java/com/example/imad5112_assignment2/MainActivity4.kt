package com.example.imad5112_assignment2

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imad5112_assignment2.MainActivity2.Question

class MainActivity4 : AppCompatActivity() {

    // List of quiz questions for display in the review
    private val questions = arrayOf(
        Question("The Great Wall of China was built to protect against invasions.", true),
        Question("World War I ended in 1950.", false),
        Question("Nelson Mandela was the first Black president of South Africa.", true),
        Question("The pyramids of Egypt were built in the 20th century.", false),
        Question("Christopher Columbus discovered America in 1492.", true)
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        // Handle insets padding (just layout-related)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the user's answers passed from the Score screen
        val answers = intent.getBooleanArrayExtra("ANSWERS")
        val reviewText = findViewById<TextView>(R.id.review_text)
        val retakeButton = findViewById<Button>(R.id.retake_button)

        val review = buildString {
            for (i in questions.indices) {
                append("Q${i + 1}: ${questions[i].questionText}\n")
                append("Correct Answer: ${questions[i].answer}\n")
                if (answers != null && i < answers.size) {
                    append("Your Answer: ${answers[i]}\n")
                }
                append("\n")
            }
        }
        // Display the built review summary in the TextView
        reviewText.text = review

        retakeButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}
