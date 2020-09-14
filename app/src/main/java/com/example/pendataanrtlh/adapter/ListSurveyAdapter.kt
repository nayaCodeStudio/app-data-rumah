package com.example.pendataanrtlh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.model.FormSurveyor
import kotlinx.android.synthetic.main.item_data_hasil_survey.view.*

/**
 ** Written by @JoeFachrizal 10/09/2020 08.52
 **/

class ListSurveyAdapter(private var dataList: ArrayList<FormSurveyor>) :
    RecyclerView.Adapter<ListSurveyAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


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
        fun bind(dataSurvey: FormSurveyor) {
            with(itemView){
                namaLengkap.text = dataSurvey.namaLengkap
                nomorKTP.text = dataSurvey.noKTPPeserta
                tglInput.text = dataSurvey.tglInput
                namaSurveyor.text = dataSurvey.nameSurveyor
            }
            itemView.setOnClickListener {
                onItemClickCallback.onClicked(dataSurvey)
            }
        }
    }

    interface OnItemClickCallback {
        fun onClicked(data: FormSurveyor)
    }
}