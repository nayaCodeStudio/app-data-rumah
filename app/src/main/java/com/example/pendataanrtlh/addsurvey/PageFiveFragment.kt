package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageFiveBinding
import com.example.pendataanrtlh.model.AspekLuasRuang
import com.example.pendataanrtlh.utils.Data.NIK_SURVEYOR
import com.example.pendataanrtlh.utils.Data.TEMP_FORM
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageFiveFragment : Fragment() {
    private lateinit var binding: FragmentPageFiveBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageFiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference(TEMP_FORM)

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_FiveFragment_to_FourFragment)
        }

        binding.btnNext.setOnClickListener {
            val inLuasRumah = binding.textLuasRumah.text.toString().trim { it <= ' ' }
            val inJumPenghuni = binding.textJumPenghuni.text.toString().trim { it <= ' ' }
            var inputKosong = false

            when {
                inLuasRumah.isEmpty() -> {
                    inputKosong = true
                    binding.textLuasRumah.error = getString(R.string.msg_error_empty)
                }
                inJumPenghuni.isEmpty() -> {
                    inputKosong = true
                    binding.textJumPenghuni.error = getString(R.string.msg_error_empty)
                }
            }

            if (!inputKosong) {
                myRef = database.getReference("$TEMP_FORM/$NIK_SURVEYOR/$nikPeserta")
                myRef
//                myRef.child(NIK_SURVEYOR).child(nikPeserta.toString())
                    .setValue(AspekLuasRuang(inLuasRumah, inJumPenghuni))
                    .addOnCompleteListener {
                        findNavController().navigate(R.id.action_FiveFragment_to_SixFragment)
                    }
            }
        }
    }
}