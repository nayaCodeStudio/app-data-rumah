package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageSixBinding
import com.example.pendataanrtlh.model.FormDataSurvey
import com.example.pendataanrtlh.model.FormSurveyor
import com.example.pendataanrtlh.utils.Data
import com.example.pendataanrtlh.utils.Data.konAtap
import com.example.pendataanrtlh.utils.Data.konDinding
import com.example.pendataanrtlh.utils.Data.konLantai
import com.example.pendataanrtlh.utils.Data.matAtap
import com.example.pendataanrtlh.utils.Data.matDinding
import com.example.pendataanrtlh.utils.Data.matLantai
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageSixFragment : Fragment() {
    private lateinit var binding: FragmentPageSixBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var myRef1: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageSixBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("${Data.DATA_SURVEY}/${Data.noKTPUser}")
        myRef1 = database.getReference("${Data.DATA_SURVEYOR}/${Data.noKTPUser}")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_sixFragment_to_sevenFragment)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_sixFragment_to_firstFragment)

            val inMatAtap = binding.listMaterialAtap.selectedItem.toString()
            val inKonAtap = binding.listKondisiAtap.selectedItem.toString()
            val inMatDinding = binding.listMaterialDinding.selectedItem.toString()
            val inKonDinding = binding.listKondisiDinding.selectedItem.toString()
            val inMatLantai = binding.listMaterialLantai.selectedItem.toString()
            val inKonLantai = binding.listKondisiLantai.selectedItem.toString()

            if (inMatAtap != "pilih" &&
                inKonAtap != "pilih" &&
                inMatDinding != "pilih" &&
                inKonDinding != "pilih" &&
                inMatLantai != "pilih" &&
                inKonLantai != "pilih"
            ) {
                //One Push Method
                matAtap = inMatAtap
                konAtap = inKonAtap
                matDinding = inMatDinding
                konDinding = inKonDinding
                matLantai = inMatLantai
                konLantai = inKonLantai

                myRef.setValue(
                    FormDataSurvey(
                        Data.nomorRumah,
                        Data.namaLengkap,
                        Data.usia,
                        Data.pendidikan,
                        Data.jenisKelamin,
                        Data.titikKoordinat,
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
                        matAtap,
                        konAtap,
                        matDinding,
                        konDinding,
                        matLantai,
                        konLantai,
                        Data.nameDesaKel,
                        Data.nameKec,
                        Data.nameKotaKab,
                        Data.nameProv,
                    )
                ).addOnCompleteListener {
                    myRef1.setValue(
                        FormSurveyor(
                            Data.namaLengkap,
                            Data.noKTPUser,
                            Data.fullName,
                            Data.noKTPSurveyor,
                            Data.tglInput,
                        )
                    )
                    findNavController().navigate(R.id.action_sixFragment_to_sevenFragment)
                }
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}