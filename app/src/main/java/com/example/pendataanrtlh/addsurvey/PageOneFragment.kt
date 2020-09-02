package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageOneBinding
import com.example.pendataanrtlh.model.FormData
import com.example.pendataanrtlh.utils.Data.NIK_SURVEYOR
import com.example.pendataanrtlh.utils.Data.TEMP_FORM
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PageOneFragment : Fragment() {
    private lateinit var binding: FragmentPageOneBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageOneBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()

        binding.btnNext.setOnClickListener {
            val inNoKTP = binding.textNomorNIK.text.toString().trim { it <= ' ' }
            val inNameDesKel = binding.textDesKel.text.toString().trim { it <= ' ' }
            val inNameKec = binding.textKec.text.toString().trim { it <= ' ' }
            val inNameKotKab = binding.textKotKab.text.toString().trim { it <= ' ' }
            val inNameProv = binding.textProv.text.toString().trim { it <= ' ' }

            var inputKosong = false
            when {
                inNoKTP.isEmpty() -> {
                    inputKosong = true
                    binding.textNomorNIK.error = getString(R.string.msg_error_empty)
                }
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
                myRef = database.getReference("$TEMP_FORM/$NIK_SURVEYOR/$inNoKTP")
                myRef
//                myRef.child(NIK_SURVEYOR).child(inNoKTP)
//                myRef.child("$NIK_SURVEYOR/$inNoKTP")
                    .setValue(FormData(inNoKTP, inNameDesKel, inNameKec, inNameKotKab, inNameProv))
                    .addOnCompleteListener {
                        nikPeserta = inNoKTP
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }
            }
        }
    }
}