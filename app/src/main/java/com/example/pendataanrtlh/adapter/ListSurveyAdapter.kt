package com.example.pendataanrtlh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.model.IdentitasPenghuniRmh
import kotlinx.android.synthetic.main.item_data_hasil_survey.view.*

/**
 ** Written by @JoeFachrizal 10/09/2020 08.52
 **/

class ListSurveyAdapter(private var dataList: ArrayList<IdentitasPenghuniRmh>) :
    RecyclerView.Adapter<ListSurveyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_data_hasil_survey, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataSurvey: IdentitasPenghuniRmh) {
            with(itemView){
                tvPemilikRumah.text = dataSurvey.nmLengkap
            }
        }
    }
}