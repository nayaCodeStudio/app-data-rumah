package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AddSurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Peringatan!")
            .setMessage("Apakah anda akan keluar dari survey?")

            .setNegativeButton("Batal") { _, _ ->
                null
            }

            .setPositiveButton("Ya") { _, _ ->
//                super.onBackPressed()
                finish()
            }
            .show()
    }
}