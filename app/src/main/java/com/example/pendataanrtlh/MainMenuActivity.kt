package com.example.pendataanrtlh

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.addsurvey.AddSurveyActivity
import com.example.pendataanrtlh.databinding.ActivityMainMenuBinding
import com.example.pendataanrtlh.model.RegisterForm
import com.example.pendataanrtlh.surveyresult.HasilSurveyActivity
import com.example.pendataanrtlh.utils.Data
import com.example.pendataanrtlh.utils.Data.fullName
import com.example.pendataanrtlh.utils.Data.noKTPSurveyor
import com.example.pendataanrtlh.utils.Data.tglInput
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var inUser: String
    private lateinit var inPassword: String

    companion object {
        const val EXTRA_USER = "data_user"
        const val EXTRA_PASS = "data_pass"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference(Data.REGISTER_FORM)

        inUser = intent.getStringExtra(EXTRA_USER).toString()
        inPassword = intent.getStringExtra(EXTRA_PASS).toString()
        onGetData(inUser, inPassword)

        onShowTime()
    }

    private fun onGetData(inUser: String, inPassword: String) {
        myRef.child(inUser).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(RegisterForm::class.java)
                if (value?.noKTP == inUser && value.password == inPassword) {
                    showData(value)
                } else {
                    Toast.makeText(
                        this@MainMenuActivity,
                        "Maaf Data Tidak Tersedia",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@MainMenuActivity,
                    "Maaf Data Tidak Tersedia",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        })
    }

    private fun showData(value: RegisterForm) {
        val sebutan = if (value.jenisKelamin == "Pria") {
            "Bapak"
        } else {
            "Ibu"
        }
        binding.txtSebutan.text = sebutan
        binding.textFullName.text = value.fullName

        binding.btnSurvey.setOnClickListener {
            val intent = Intent(this@MainMenuActivity, AddSurveyActivity::class.java)
            startActivity(intent)
            noKTPSurveyor = value.noKTP
            fullName = value.fullName
        }

        binding.btnSurveyResult.setOnClickListener {
            val intent = Intent(this, HasilSurveyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onShowTime() {
        val thread = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            val date = System.currentTimeMillis()
                            val sdf = SimpleDateFormat("dd MMM yyyy, h:mm a", Locale.getDefault())
                            val dateString = sdf.format(date)
                            tglInput = dateString
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }
        thread.start()
    }
}