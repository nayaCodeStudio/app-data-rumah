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
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_page_three.*


class PageThreeFragment : Fragment() {
    private lateinit var binding: FragmentPageThreeBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var pondasi: String? = ""
    private var balok: String? = ""
    private var atap: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageThreeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("$USER_DATA/$nikPeserta/$ASPEK_KESELAMATAN")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        binding.btnNext.setOnClickListener {
            pondasi = if (chip_ada.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            balok = binding.listKondisiKolom.selectedItem.toString()
            atap = binding.listKondisiKonstruksi.selectedItem.toString()

            if (!pondasi.isNullOrEmpty() &&  balok != "pilih" && atap != "pilih") {
                myRef.setValue(AspekKeselamatan(pondasi, balok, atap))
                    .addOnCompleteListener {
                        findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)
                    }
            } else {
                Toast.makeText(context, "Harap Diisi dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}