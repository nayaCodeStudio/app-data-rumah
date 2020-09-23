package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageThreeBinding
import com.example.pendataanrtlh.utils.Data.atap
import com.example.pendataanrtlh.utils.Data.balok
import com.example.pendataanrtlh.utils.Data.pondasi
import kotlinx.android.synthetic.main.fragment_page_three.*


class PageThreeFragment : Fragment() {
    private lateinit var binding: FragmentPageThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageThreeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)

            val inPondasi = if (chip_ada.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            val inBalok = binding.listKondisiKolom.selectedItem.toString()
            val inAtap = binding.listKondisiKonstruksi.selectedItem.toString()

            if (!inPondasi.isEmpty() &&
                inBalok != "pilih" &&
                inAtap != "pilih") {
                //One Push Methode
                pondasi = inPondasi
                balok = inBalok
                atap = inAtap

                findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}