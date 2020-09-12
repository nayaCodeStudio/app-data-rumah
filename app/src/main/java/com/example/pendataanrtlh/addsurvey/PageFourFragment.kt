package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageFourBinding
import com.example.pendataanrtlh.model.AspekKesehatan
import com.example.pendataanrtlh.utils.Data.ASPEK_KESEHATAN
import com.example.pendataanrtlh.utils.Data.USER_DATA
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PageFourFragment : Fragment() {
    private lateinit var binding: FragmentPageFourBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var jendela: String? = ""
    private var ventilasi: String? = ""
    private var kmrMandi: String? = ""
    private var jrkKmrMandi: String? = ""
    private var sumAirMinum: String? = ""
    private var sumListrik: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("$USER_DATA/$nikPeserta/$ASPEK_KESEHATAN")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_FourFragment_to_ThirdFragment)
        }

        binding.btnNext.setOnClickListener {

            jendela = if (binding.chipAdaJendela.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            ventilasi = if (binding.chipAdaVentilasi.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            kmrMandi = binding.listKepemilikanKamarMandi.selectedItem.toString()
            jrkKmrMandi = if (binding.chipKurang.isChecked) {
                "Kurang dari 10 Meter"
            } else {
                "Lebih dari 10 Meter"
            }
            sumAirMinum = binding.listSumberAirMinum.selectedItem.toString()
            sumListrik = binding.listSumberListik.selectedItem.toString()

            if (ventilasi != "pilih" && kmrMandi != "pilih" && sumAirMinum != "pilih" && sumListrik != "pilih") {
                myRef.setValue(
                    AspekKesehatan(
                        jendela,
                        ventilasi,
                        kmrMandi,
                        jrkKmrMandi,
                        sumAirMinum,
                        sumListrik,
                    )
                )
                    .addOnCompleteListener {
                        findNavController().navigate(R.id.action_FourFragment_to_FiveFragment)
                    }
            } else {
                Toast.makeText(context, "Harap Diisi dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}