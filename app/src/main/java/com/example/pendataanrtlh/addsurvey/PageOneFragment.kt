package com.example.pendataanrtlh.addsurvey

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageOneBinding
import com.example.pendataanrtlh.model.FormDataSurvey
import com.example.pendataanrtlh.model.FormSurveyor
import com.example.pendataanrtlh.utils.Data
import com.example.pendataanrtlh.utils.Data.DATA_SURVEY
import com.example.pendataanrtlh.utils.Data.DATA_SURVEYOR
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PageOneFragment : Fragment() {
    private lateinit var binding: FragmentPageOneBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var myRef1: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("$DATA_SURVEY/${Data.noKTPUser}")
        myRef1 = database.getReference("$DATA_SURVEYOR/${Data.noKTPUser}")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_sixFragment)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_sevenFragment)

            val inNameDesKel = binding.textDesKel.text.toString().trim { it <= ' ' }
            val inNameKec = binding.textKec.text.toString().trim { it <= ' ' }
            val inNameKotKab = binding.textKotKab.text.toString().trim { it <= ' ' }
            val inNameProv = binding.textProv.text.toString().trim { it <= ' ' }

            var inputKosong = false
            when {
                inNameDesKel.isEmpty() -> {
                    inputKosong = true
                    binding.textDesKel.error = getString(R.string.msg_error_empty)
                }
                inNameKec.isEmpty() -> {
                    inputKosong = true
                    binding.textKec.error = getString(R.string.msg_error_empty)
                }
                inNameKotKab.isEmpty() -> {
                    inputKosong = true
                    binding.textKotKab.error = getString(R.string.msg_error_empty)
                }
                inNameProv.isEmpty() -> {
                    inputKosong = true
                    binding.textProv.error = getString(R.string.msg_error_empty)
                }
            }
            if (!inputKosong) {
                //One Push Methode
                Data.nameDesaKel = inNameDesKel
                Data.nameKec = inNameKec
                Data.nameKotaKab = inNameKotKab
                Data.nameProv = inNameProv

                myRef.setValue(
                    FormDataSurvey(
                        Data.nomorRumah,
                        Data.namaLengkap,
                        Data.usia,
                        Data.pendidikan,
                        Data.jenisKelamin,
                        Data.almLengkp,
                        Data.noKTPUser,
                        Data.jumlhKK,
                        Data.pekerjaan,
                        Data.penghasilan,
                        Data.statusTanah,
                        Data.statusRumah,
                        Data.assetRumah,
                        Data.assetTanah,
                        Data.bantuanRumah,
                        Data.kawasanLokasi,
                        Data.pondasi,
                        Data.balok,
                        Data.atap,
                        Data.jendela,
                        Data.ventilasi,
                        Data.kmrMandi,
                        Data.jrkKmrMandi,
                        Data.sumAirMinum,
                        Data.sumListrik,
                        Data.luasRumah,
                        Data.jumPenghuni,
                        Data.matAtap,
                        Data.konAtap,
                        Data.matDinding,
                        Data.konDinding,
                        Data.matLantai,
                        Data.konLantai,
                        Data.nameDesaKel,
                        Data.nameKec,
                        Data.nameKotaKab,
                        Data.nameProv,
                    )
                )
                    .addOnCompleteListener {
                        myRef1.setValue(
                            FormSurveyor(
                                Data.namaLengkap,
                                Data.noKTPUser,
                                Data.fullName,
                                Data.noKTPSurveyor,
                                Data.tglInput,
                            )
                        )
                        findNavController().navigate(R.id.action_firstFragment_to_sevenFragment)
                    }

            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}