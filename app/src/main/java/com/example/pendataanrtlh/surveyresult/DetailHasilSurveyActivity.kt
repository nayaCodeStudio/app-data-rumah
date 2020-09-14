package com.example.pendataanrtlh.surveyresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.ActivityDetailHasilSurveyBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailHasilSurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHasilSurveyBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHasilSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

    }
}