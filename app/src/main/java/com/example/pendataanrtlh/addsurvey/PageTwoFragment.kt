package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageTwoBinding
import com.example.pendataanrtlh.model.IdentitasPenghuniRmh
import com.example.pendataanrtlh.utils.Data.IDENTITAS_PENGHUNI_RMH
import com.example.pendataanrtlh.utils.Data.TEMP_FORM
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageTwoFragment : Fragment() {
    private lateinit var binding: FragmentPageTwoBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

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
        }
    }
}