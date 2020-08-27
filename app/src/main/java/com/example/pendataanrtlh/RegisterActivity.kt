package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        btnLogin.setOnClickListener {
            val intentLoginActivity =
                Intent(Intent(this, LoginActivity::class.java))
            startActivity(intentLoginActivity)
            Toast.makeText(this@RegisterActivity, "Silahkan login!", Toast.LENGTH_SHORT)
                .show()
        }

        btnRegister.setOnClickListener {
            val intentRegisterActivity =
                Intent(Intent(this, LoginActivity::class.java))
            startActivity(intentRegisterActivity)
            Toast.makeText(this@RegisterActivity, "Registrasi data berhasil, silahkan login!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}