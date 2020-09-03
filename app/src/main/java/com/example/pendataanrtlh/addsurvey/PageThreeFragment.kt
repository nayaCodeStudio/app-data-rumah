package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_page_three.*


class PageThreeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_three, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chip1 = "Ada"
        val chip2 = "Tidak Ada"

        view.findViewById<Button>(R.id.btnPrev).setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)
            if (chip_ada.isChecked){
                Toast.makeText(view.context, "Pondasi : "  + chip1, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "Pondasi : "  + chip2, Toast.LENGTH_SHORT).show()
            }
        }
    }
}