package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pendataanrtlh.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private var inNoKTP: String? = ""
    private var inPassword: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intentLoginActivity =
                Intent(Intent(this, MainMenuActivity::class.java))
            startActivity(intentLoginActivity)
            Toast.makeText(this@LoginActivity, "Login berhasil!", Toast.LENGTH_SHORT)
                .show()
//            finish()
        }

        binding.btnLogin.setOnLongClickListener {
            val intentLoginActivity =
                Intent(Intent(this, RegisterActivity::class.java))
            startActivity(intentLoginActivity)
            Toast.makeText(this@LoginActivity, "Login berhasil!", Toast.LENGTH_SHORT)
                .show()
            true
        }
    }
}