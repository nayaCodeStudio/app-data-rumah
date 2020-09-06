package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageTwoBinding
import com.example.pendataanrtlh.model.AspekKeselamatan
import com.example.pendataanrtlh.model.AspekLuasRuang
import com.example.pendataanrtlh.model.IdentitasPenghuniRmh
import com.example.pendataanrtlh.utils.Data.IDENTITAS_PENGHUNI_RMH
import com.example.pendataanrtlh.utils.Data.TEMP_FORM
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_page_three.*
import kotlinx.android.synthetic.main.fragment_page_two.*


class PageTwoFragment : Fragment() {
    private lateinit var binding: FragmentPageTwoBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var jenisKelamin: String? = ""
    private var pendidikanTrakhir: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("$TEMP_FORM/$nikPeserta/$IDENTITAS_PENGHUNI_RMH")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.btnNext.setOnClickListener {
            val inNomorRumah = binding.textNoRumah.text.toString().trim { it <= ' ' }
            val inNamaLengkap = binding.textNmLengkap.text.toString().trim { it <= ' ' }
            val inUsia = binding.textUsia.text.toString().trim { it <= ' ' }
            var inputKosong = false

            when {
                inNomorRumah.isEmpty() -> {
                    inputKosong = true
                    binding.textNoRumah.error = getString(R.string.msg_error_empty)
                }
                inNamaLengkap.isEmpty() -> {
                    inputKosong = true
                    binding.textNmLengkap.error = getString(R.string.msg_error_empty)
                }
                inUsia.isEmpty() -> {
                    inputKosong = true
                    binding.textUsia.error = getString(R.string.msg_error_empty)
                }
            }

            if (!inputKosong) {

            }

            pendidikanTrakhir = binding.listPendidikan.selectedItem.toString()

            jenisKelamin = if (chip_pria.isChecked) {
                "Pria"
            } else {
                "Wanita"
            }

            if (!jenisKelamin.isNullOrEmpty() && pendidikanTrakhir != "pilih") {
                myRef.setValue(
                    IdentitasPenghuniRmh(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
                    .addOnCompleteListener {
                        findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
                    }
            } else {
                Toast.makeText(context, "Harap Diisi dahulu", Toast.LENGTH_SHORT).show()
            }


        }
    }
}