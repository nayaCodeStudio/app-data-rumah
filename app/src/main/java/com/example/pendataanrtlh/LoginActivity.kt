package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pendataanrtlh.databinding.ActivityLoginBinding
import com.example.pendataanrtlh.model.RegisterForm
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var inUser: String
    private lateinit var inPassword: String
    private var strUser: String? = "12345"
    private var strPass: String? = "rtlh123"
    private var admPass: String? = "adminrtlh"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.logoApp.setOnClickListener {
//            val intentLoginActivity =
//                Intent(Intent(this, UploadPhotoActivity::class.java))
//            startActivity(intentLoginActivity)
//        }

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
                if (inUser == strUser && inPassword == strPass) {
                    val intentLoginActivity = Intent(Intent(this, MainMenuActivity::class.java))
                    startActivity(intentLoginActivity)
                    finish()
                } else {
                    Toast.makeText(this, "Maaf Data Tidak Tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnLogin.setOnLongClickListener {
            if (inUser == strUser && inPassword == admPass) {
                val intentLoginActivity =
                    Intent(Intent(this, RegisterActivity::class.java))
                startActivity(intentLoginActivity)
                Toast.makeText(this@LoginActivity, "Login berhasil!", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }
    }
}