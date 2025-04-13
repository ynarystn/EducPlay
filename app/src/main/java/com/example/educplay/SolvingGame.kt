package com.example.educplay

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SolvingGame : FullscreenActivity() {

    private lateinit var answerButtons: List<ImageButton>
    private lateinit var resultPopup: ImageView
    private val correctAnswerIndex = 2 // Index of the correct button (0-based)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solving_game)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultPopup = findViewById(R.id.resultPopup)

        // Link the 5 answer buttons
        val answerButtons = listOf(
            findViewById<ImageButton>(R.id.answer_button_1),
            findViewById<ImageButton>(R.id.answer_button_2),
            findViewById<ImageButton>(R.id.answer_button_3),
            findViewById<ImageButton>(R.id.answer_button_4),
            findViewById<ImageButton>(R.id.answer_button_5),
        )

        // Set click listener for each
        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (index == correctAnswerIndex) {
                    button.setImageResource(R.drawable.sg_button_correct)
                    showPopup(true)
                } else {
                    button.setImageResource(R.drawable.sg_button_wrong)
                    showPopup(false)
                }
            }
        }
    }

    private fun showPopup(isCorrect: Boolean) {
        resultPopup.setImageResource(if (isCorrect) R.drawable.sg_correct else R.drawable.sg_wrong)
        resultPopup.visibility = ImageView.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            resultPopup.visibility = ImageView.GONE
        }, 2000) // 2 seconds
    }
}
