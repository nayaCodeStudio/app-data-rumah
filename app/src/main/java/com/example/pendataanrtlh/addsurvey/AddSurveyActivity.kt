package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.R


class AddSurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}