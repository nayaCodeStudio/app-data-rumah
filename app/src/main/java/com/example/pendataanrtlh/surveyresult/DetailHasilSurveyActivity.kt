package com.example.pendataanrtlh.surveyresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.ActivityDetailHasilSurveyBinding
import com.example.pendataanrtlh.model.FormSurveyor
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailHasilSurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHasilSurveyBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    companion object {
        const val NAMA_USER = "nama_user"
        const val NIK_USER = "nik_user"
        const val TGL_INPUT = "tgl_input"
        const val NAMA_SURVEYOR = "nama_surveyor"
        const val NIK_SURVEYOR = "nik_surveyor"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHasilSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        val namaUser = intent.getStringExtra(NAMA_USER)
        val nikUser = intent.getStringExtra(NIK_USER)
        val tglInput = intent.getStringExtra(TGL_INPUT)
        val namaSurveyor = intent.getStringExtra(NAMA_SURVEYOR)
        val nikSurveyor = intent.getStringExtra(NIK_SURVEYOR)

        binding.namaLengkap.text = namaUser
        binding.nomorKTP.text = nikUser
        binding.tglInput.text = tglInput
        binding.namaSurveyor.text = namaSurveyor
    }
}