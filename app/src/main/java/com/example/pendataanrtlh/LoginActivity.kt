package com.example.pendataanrtlh

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.pendataanrtlh.databinding.ActivityLoginBinding
import com.example.pendataanrtlh.model.RegisterForm
import com.example.pendataanrtlh.model.Status
import com.example.pendataanrtlh.utils.Data
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.*
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var myRef1: DatabaseReference
    private lateinit var inUser: String
    private lateinit var inPassword: String
    private lateinit var countDownTimer: CountDownTimer

    //    private var timeValue: Long = 1
    private var beta: String? = "beta"
    private var trial: String? = "trial"
    private var full: String? = "full"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference(Data.REGISTER_FORM)

        onLock()

//        binding.btnLogin.setOnLongClickListener {
//            if (inUser == strUser && inPassword == admPass) {
//                val intent = Intent(this, RegisterActivity::class.java)
//                startActivity(intent)
//                Toast.makeText(this@LoginActivity, "Login berhasil!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            true
//        }
    }

    private fun onLock() {
        myRef1 = database.getReference("status")
        myRef1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val versionCode = snapshot.getValue(Status::class.java)

                when (versionCode?.versiKode) {
                    beta -> {
                        onShowDialog("Aplikasi Sedang Ada perbaikan")
                    }
                    trial -> {
                        onShowDialog("Anda Sedang Berada di versi Percobaan")
                    }
                    full -> {
                        onShowDialog("Selamat Datang Di e-Sarulah")
                    }
                }

                binding.btnLogin.setOnClickListener {
                    inUser = binding.textNomorKTP.text.toString().trim { it <= ' ' }
                    inPassword = binding.textPassword.text.toString().trim { it <= ' ' }

                    var inputKosong = false
                    when {
                        inUser.isEmpty() -> {
                            inputKosong = true
                            binding.textNomorKTP.error = getString(R.string.msg_error_empty)
                        }
                        inPassword.isEmpty() -> {
                            inputKosong = true
                            binding.textPassword.error = getString(R.string.msg_error_empty)
                        }
                    }
                    if (!inputKosong) {
                        when (versionCode?.versiKode) {
                            beta -> {
                                onShowDialog(versionCode?.pesan.toString())
                            }
                            trial -> {
                                onCountDownTimer(inUser, inPassword)
                            }
                            else -> {
                                onGetData(inUser, inPassword)
                            }
                        }
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun onGetData(inUser: String, inPassword: String) {
        myRef.child(inUser).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(RegisterForm::class.java)
                if (value?.noKTP == inUser && value.password == inPassword) {
                    val intentLoginActivity =
                        Intent(this@LoginActivity, MainMenuActivity::class.java)
                    intentLoginActivity.putExtra(MainMenuActivity.EXTRA_USER, value.noKTP)
                    intentLoginActivity.putExtra(MainMenuActivity.EXTRA_PASS, value.password)
                    startActivity(intentLoginActivity)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Maaf Data Tidak Tersedia",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Maaf Data Tidak Tersedia", Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }

    private fun onShowDialog(pesan: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Peringatan!")
            .setMessage(pesan)

            .setPositiveButton("Ya") { _, _ ->
                null
            }
            .show()
    }

    private fun onCountDownTimer(inUser: String, inPassword: String) {
        binding.btnLogin.isEnabled = false
        binding.btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.textColor2))
        var timeValue: Long = 1
        countDownTimer = object : CountDownTimer(timeValue * 30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(timeFinished: Long) {
                timeValue = timeFinished

                val hours = (timeValue / 1000).toInt() / 3600
                val minutes = (timeValue / 1000 % 3600).toInt() / 60
                val seconds = (timeValue / 1000).toInt() % 60

                val timeLeftFormatted: String
                timeLeftFormatted = if (hours > 0) {
                    String.format(
                        Locale.getDefault(), "%02d:%02d",  minutes, seconds
                    )
                } else {
                    String.format(
                        Locale.getDefault(), "%02d", seconds
                    )
                }
                binding.btnLogin.text = "Masuk dalam $timeLeftFormatted detik"
            }

            override fun onFinish() {
                val text = "Persiapan..."
//                binding.btnLogin.isEnabled = true
                binding.btnLogin.setBackgroundColor(
                    ContextCompat.getColor(
                        this@LoginActivity,
                        R.color.bgBiru
                    )
                )
                binding.btnLogin.text = text
                onGetData(inUser, inPassword)
            }

        }.start()
    }
}