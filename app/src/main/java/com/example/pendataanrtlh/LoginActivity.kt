package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        btnLogin.setOnClickListener {
            val intentLoginActivity =
            Intent(Intent(this, MainMenuActivity::class.java))
            startActivity(intentLoginActivity)
            Toast.makeText(this@LoginActivity, "Login berhasil!", Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        btnRegister.setOnClickListener {
            val intentRegisterActivity =
            Intent(Intent(this, RegisterActivity::class.java))
            startActivity(intentRegisterActivity)
            Toast.makeText(this@LoginActivity, "Registrasi data!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}