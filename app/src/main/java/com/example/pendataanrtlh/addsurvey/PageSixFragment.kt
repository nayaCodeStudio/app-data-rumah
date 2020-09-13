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
import com.example.pendataanrtlh.utils.Data.konAtap
import com.example.pendataanrtlh.utils.Data.konDinding
import com.example.pendataanrtlh.utils.Data.konLantai
import com.example.pendataanrtlh.utils.Data.matAtap
import com.example.pendataanrtlh.utils.Data.matDinding
import com.example.pendataanrtlh.utils.Data.matLantai


class PageSixFragment : Fragment() {
    private lateinit var binding: FragmentPageSixBinding
//    private lateinit var database: FirebaseDatabase
//    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageSixBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        database = FirebaseDatabase.getInstance()
//        myRef = database.getReference("$USER_DATA/$nikPeserta/$ASPEK_BANGUNAN")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_SixFragment_to_FiveFragment)
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

                findNavController().navigate(R.id.action_sixFragment_to_firstFragment)
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}