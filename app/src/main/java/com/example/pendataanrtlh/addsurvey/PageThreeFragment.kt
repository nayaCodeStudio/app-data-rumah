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
import com.example.pendataanrtlh.model.AspekKeselamatan
import com.example.pendataanrtlh.utils.Data.ASPEK_KESELAMATAN
import com.example.pendataanrtlh.utils.Data.USER_DATA
import com.example.pendataanrtlh.utils.Data.atap
import com.example.pendataanrtlh.utils.Data.balok
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.example.pendataanrtlh.utils.Data.pondasi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_page_three.*


class PageThreeFragment : Fragment() {
    private lateinit var binding: FragmentPageThreeBinding
//    private lateinit var database: FirebaseDatabase
//    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageThreeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        database = FirebaseDatabase.getInstance()
//        myRef = database.getReference("$USER_DATA/$nikPeserta/$ASPEK_KESELAMATAN")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        binding.btnNext.setOnClickListener {
            val inPondasi = if (chip_ada.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            val inBalok = binding.listKondisiKolom.selectedItem.toString()
            val inAtap = binding.listKondisiKonstruksi.selectedItem.toString()

            if (!inPondasi.isEmpty() && inBalok != "pilih" && inAtap != "pilih") {
                pondasi = inPondasi
                balok = inBalok
                atap = inAtap

                findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)
            } else {
                Toast.makeText(context, "Harap Diisi dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}