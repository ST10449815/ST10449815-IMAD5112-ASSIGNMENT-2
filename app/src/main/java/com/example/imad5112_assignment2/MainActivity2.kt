package com.example.imad5112_assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    data class Question(val questionText: String, val answer: Boolean)

    // Array of quiz questions
    private val questions = arrayOf(
        Question("The Great Wall of China was built to protect against invasions.", true),
        Question("World War I ended in 1950.", false),
        Question("Nelson Mandela was the first Black president of South Africa.", true),
        Question("The pyramids of Egypt were built in the 20th century.", false),
        Question("Christopher Columbus discovered America in 1492.", true)
    )

    private var currentIndex = 0
    // Keep score and store user answers
    private var score = 0
    private val userAnswers = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val questionText = findViewById<TextView>(R.id.question_text)
        val trueButton = findViewById<Button>(R.id.true_button)
        val falseButton = findViewById<Button>(R.id.false_button)
        val nextButton = findViewById<Button>(R.id.next_button)

        fun updateQuestion() {
            questionText.text = questions[currentIndex].questionText
        }

        // Check if user's answer is correct and show a toast
        fun checkAnswer(userAnswer: Boolean) {
            val correctAnswer = questions[currentIndex].answer
            userAnswers.add(userAnswer)
            if (userAnswer == correctAnswer) {
                score++
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
        // Set onClick listeners for True and False buttons
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        // Handle next button to go to the next question or show score screen
        nextButton.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                updateQuestion()
            } else {
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("ANSWERS", userAnswers.toBooleanArray())
                startActivity(intent)
                finish()
            }
        }

        updateQuestion()
    }
}
