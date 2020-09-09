package com.example.pendataanrtlh

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.addsurvey.AddSurveyActivity
import com.example.pendataanrtlh.databinding.ActivityMainMenuBinding
import com.example.pendataanrtlh.model.FormData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private var noKTP = "01020304050607"
    private var inDesKel = "Cilendek Timur"
    private var inKec = "Bogor Barat"
    private var inkabKot = "Kota Bogor"
    private var inProv = "Jawa Barat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSurvey.setOnClickListener {
            val intentLoginActivity =
                Intent(Intent(this, AddSurveyActivity::class.java))
            startActivity(intentLoginActivity)
        }

        binding.btnSurveyResult.setOnClickListener {
//            realTimeDB(FormData(noKTP, inDesKel, inKec, inkabKot, inProv))
        }
    }

    private fun realTimeDB(formData: FormData) {
        val database = Firebase.database
        val myRef = database.getReference("message")

//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("message")
//
        myRef.setValue(formData)


//        myRef.setValue(formData).addOnCompleteListener {
            Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show()
//        }
    }
}