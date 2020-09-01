package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PageFiveFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_five, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnPrev).setOnClickListener {
            findNavController().navigate(R.id.action_FiveFragment_to_FourFragment)
        }

        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            findNavController().navigate(R.id.action_FiveFragment_to_SixFragment)
        }
    }
}