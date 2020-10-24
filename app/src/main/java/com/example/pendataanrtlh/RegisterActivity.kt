package com.example.pendataanrtlh

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.databinding.ActivityRegisterBinding
import com.example.pendataanrtlh.model.RegisterForm
import com.example.pendataanrtlh.utils.Data.REGISTER_FORM
import com.google.firebase.database.*


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference(REGISTER_FORM)

        setData()
    }

    private fun setData() {
        binding.btnRegister.setOnClickListener {
            val inEmailAddress = binding.alamatEmail.text.toString().trim { it <= ' ' }
            val inPassword = binding.textPassword.text.toString().trim { it <= ' ' }
            val inFullName = binding.textFullName.text.toString().trim { it <= ' ' }
            val inNoKTP = binding.textNomorKTP.text.toString().trim { it <= ' ' }
            val inNoHp = binding.nomorHP.text.toString().trim { it <= ' ' }
            val inAddress = binding.textAddress.text.toString().trim { it <= ' ' }

            val inJenisKelamin = if (binding.chipPria.isChecked) {
                "Pria"
            } else {
                "Wanita"
            }

            var inputKosong = false
            when {
                inEmailAddress.isEmpty() -> {
                    inputKosong = true
                    binding.alamatEmail.error = getString(R.string.msg_error_empty)
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
                    binding.nomorHP.error = getString(R.string.msg_error_empty)
                }
                inAddress.isEmpty() -> {
                    inputKosong = true
                    binding.textAddress.error = getString(R.string.msg_error_empty)
                }
            }
            if (!inputKosong) {
                myRef.child(inNoKTP).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.getValue(RegisterForm::class.java)
                        Toast.makeText(this@RegisterActivity, "${value?.noKTP}", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

                realTimeDB(
                    inNoKTP,
                    RegisterForm(
                        inNoKTP,
                        inPassword,
                        inFullName,
                        inEmailAddress,
                        inNoHp,
                        inAddress,
                        inJenisKelamin
                    )
                )
            }
        }
    }

    // bug data sudah ada
    private fun realTimeDB(inNoKTP: String, registerForm: RegisterForm) {
        myRef.child(inNoKTP).setValue(registerForm).addOnCompleteListener {
            Toast.makeText(
                this@RegisterActivity,
                "Registrasi data berhasil",
                Toast.LENGTH_SHORT
            )
                .show()
//            finish()
        }
    }
}