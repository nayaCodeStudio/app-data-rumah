package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pendataanrtlh.addsurvey.AddSurveyActivity
import com.example.pendataanrtlh.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSurvey.setOnClickListener {
            val intentLoginActivity =
                Intent(Intent(this, AddSurveyActivity::class.java))
            startActivity(intentLoginActivity)
        }
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