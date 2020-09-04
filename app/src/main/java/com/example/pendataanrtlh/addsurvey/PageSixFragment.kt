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
import com.example.pendataanrtlh.model.AspekBangunan
import com.example.pendataanrtlh.model.AspekKeselamatan
import com.example.pendataanrtlh.utils.Data.ASPEK_BANGUNAN
import com.example.pendataanrtlh.utils.Data.TEMP_FORM
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageSixFragment : Fragment() {
    private lateinit var binding: FragmentPageSixBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var matAtap: String? = ""
    private var konAtap: String? = ""
    private var matDinding: String? = ""
    private var konDinding: String? = ""
    private var matLantai: String? = ""
    private var konLantai: String? = ""

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
        myRef = database.getReference("$TEMP_FORM/$nikPeserta/$ASPEK_BANGUNAN")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_SixFragment_to_FiveFragment)
        }

        binding.btnNext.setOnClickListener {
            matAtap = binding.listMaterialAtap.selectedItem.toString()
            konAtap = binding.listKondisiAtap.selectedItem.toString()
            matDinding = binding.listMaterialDinding.selectedItem.toString()
            konDinding = binding.listKondisiDinding.selectedItem.toString()
            matLantai = binding.listMaterialLantai.selectedItem.toString()
            konLantai = binding.listKondisiLantai.selectedItem.toString()

            if (matAtap != "pilih" && konAtap != "pilih" && matDinding != "pilih" && konDinding != "pilih" && matLantai != "pilih" && konLantai != "pilih") {
                myRef.setValue(
                    AspekBangunan(
                        matAtap,
                        konAtap,
                        matDinding,
                        konDinding,
                        matLantai,
                        konLantai
                    )
                )
                    .addOnCompleteListener {
                        findNavController().navigate(R.id.action_SixFragment_to_SevenFragment)
                    }
            } else {
                Toast.makeText(context, "Harap Diisi dahulu", Toast.LENGTH_SHORT).show()
            }


        }
    }
}