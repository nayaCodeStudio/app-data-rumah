package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageFiveBinding
import com.example.pendataanrtlh.model.AspekLuasRuang
import com.example.pendataanrtlh.utils.Data.ASPEK_LUAS_RUANG
import com.example.pendataanrtlh.utils.Data.USER_DATA
import com.example.pendataanrtlh.utils.Data.jumPenghuni
import com.example.pendataanrtlh.utils.Data.luasRumah
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageFiveFragment : Fragment() {
    private lateinit var binding: FragmentPageFiveBinding
//    private lateinit var database: FirebaseDatabase
//    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageFiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        database = FirebaseDatabase.getInstance()
//        myRef = database.getReference("$USER_DATA/$nikPeserta/$ASPEK_LUAS_RUANG")

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
                //One Push Method
                luasRumah = inLuasRumah
                jumPenghuni = inJumPenghuni

                findNavController().navigate(R.id.action_FiveFragment_to_SixFragment)
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}