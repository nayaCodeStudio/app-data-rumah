package com.example.pendataanrtlh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pendataanrtlh.databinding.ActivityRegisterBinding
import com.example.pendataanrtlh.model.RegisterForm
import com.example.pendataanrtlh.utils.Data.REGISTER_FORM
import com.google.firebase.database.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private var inNoKTP = ""
    private var inPassword = ""
    private var inFullName = ""
    private var inEmailAddress = ""
    private var inNoHp = ""
    private var inAddress = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()

        setData()
    }

    private fun setData() {
        binding.btnRegister.setOnClickListener {
            inEmailAddress = binding.textEmail.text.toString().trim { it <= ' ' }
            inPassword = binding.textPassword.text.toString().trim { it <= ' ' }
            inFullName = binding.textFullName.text.toString().trim { it <= ' ' }
            inNoKTP = binding.textNomorKTP.text.toString().trim { it <= ' ' }
            inNoHp = binding.textNomorHP.text.toString().trim { it <= ' ' }
            inAddress = binding.textAlamat.text.toString().trim { it <= ' ' }

            var inputKosong = false
            when {
                inEmailAddress.isEmpty() -> {
                    inputKosong = true
                    binding.textEmail.error = getString(R.string.msg_error_empty)
                }
                inPassword.isEmpty() -> {
                    inputKosong = true
                    binding.textPassword.error = getString(R.string.msg_error_empty)
                }
                inFullName.isEmpty() -> {
                    inputKosong = true
                    binding.textFullName.error = getString(R.string.msg_error_empty)
                }
                inNoKTP.isEmpty() -> {
                    inputKosong = true
                    binding.textNomorKTP.error = getString(R.string.msg_error_empty)
                }
                inNoHp.isEmpty() -> {
                    inputKosong = true
                    binding.textNomorHP.error = getString(R.string.msg_error_empty)
                }
                inAddress.isEmpty() -> {
                    inputKosong = true
                    binding.textAlamat.error = getString(R.string.msg_error_empty)
                }
            }
            if (!inputKosong) {
                realTimeDB(
                    RegisterForm(
                        inNoKTP,
                        inPassword,
                        inFullName,
                        inEmailAddress,
                        inNoHp,
                        inAddress
                    )
                )
            }
        }

    }

// bug data sudah ada
    private fun realTimeDB(registerForm: RegisterForm) {
        myRef = database.getReference(REGISTER_FORM).child(inNoKTP)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(RegisterForm::class.java)
                if (value?.noKTP.isNullOrEmpty()) {
                    myRef.setValue(registerForm).addOnCompleteListener {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registrasi data berhasil",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Data Sudah Ada", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}