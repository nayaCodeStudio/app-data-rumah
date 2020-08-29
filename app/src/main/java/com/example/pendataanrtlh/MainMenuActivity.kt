package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        supportActionBar?.hide()

//        buttonSurvey.setOnClickListener {
//            val intentLoginActivity =
//                Intent(Intent(this, MainActivity::class.java))
//            startActivity(intentLoginActivity)
//            Toast.makeText(this@MainMenuActivity, "Survey data!", Toast.LENGTH_SHORT)
//                .show()
//        }
//
//        buttonHasilSurvey.setOnClickListener {
//            val intentHasilSurveyActivity =
//                Intent(Intent(this,HasilSurveyActivity::class.java))
//            startActivity(intentHasilSurveyActivity)
//            Toast.makeText(this@MainMenuActivity, "Hasil data survey!", Toast.LENGTH_SHORT)
//                .show()
//        }
    }
}