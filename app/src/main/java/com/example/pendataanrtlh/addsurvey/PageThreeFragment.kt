package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R


class PageThreeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnPrev).setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)
        }
    }
}