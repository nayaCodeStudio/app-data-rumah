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
import com.example.pendataanrtlh.utils.Data.jendela
import com.example.pendataanrtlh.utils.Data.jrkKmrMandi
import com.example.pendataanrtlh.utils.Data.kmrMandi
import com.example.pendataanrtlh.utils.Data.sumAirMinum
import com.example.pendataanrtlh.utils.Data.sumListrik
import com.example.pendataanrtlh.utils.Data.ventilasi

class PageFourFragment : Fragment() {
    private lateinit var binding: FragmentPageFourBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_FourFragment_to_ThirdFragment)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_FourFragment_to_FiveFragment)

            val inJendela = if (binding.chipAdaJendela.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            val inVentilasi = if (binding.chipAdaVentilasi.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            val inJrkKmrMandi = if (binding.chipKurang.isChecked) {
                "Kurang dari 10 Meter"
            } else {
                "Lebih dari 10 Meter"
            }

            val inSumAirMinum = binding.listSumberAirMinum.selectedItem.toString()
            val inSumListrik = binding.listSumberListik.selectedItem.toString()
            val inKmrMandi = binding.listKepemilikanKamarMandi.selectedItem.toString()

            if (inKmrMandi != "pilih" &&
                inSumAirMinum != "pilih" &&
                inSumListrik != "pilih") {
                //One Push Methode
                ventilasi = inVentilasi
                kmrMandi = inKmrMandi
                sumAirMinum = inSumAirMinum
                sumListrik = inSumListrik
                jendela = inJendela
                jrkKmrMandi = inJrkKmrMandi

                findNavController().navigate(R.id.action_FourFragment_to_FiveFragment)
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}