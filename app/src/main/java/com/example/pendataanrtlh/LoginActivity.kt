package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pendataanrtlh.databinding.ActivityLoginBinding
import com.example.pendataanrtlh.model.RegisterForm
import com.example.pendataanrtlh.utils.Data
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var inUser: String
    private lateinit var inPassword: String
    private var strUser: String? = "12345"
    private var admPass: String? = "adminrtlh"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference(Data.REGISTER_FORM)

        binding.logoApp.setOnClickListener {
            val intentLoginActivity =
                Intent(Intent(this, UploadPhotoActivity::class.java))
            startActivity(intentLoginActivity)
        }

        binding.textNomorKTP.setText("12345")
        binding.textPassword.setText("1")

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
                onGetData(inUser, inPassword)
            }
        }

        binding.btnLogin.setOnLongClickListener {
            if (inUser == strUser && inPassword == admPass) {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@LoginActivity, "Login berhasil!", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }
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
}