package com.example.pendataanrtlh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pendataanrtlh.databinding.ItemDataHasilSurveyBinding
import com.example.pendataanrtlh.model.FormSurveyor
import java.util.*

/**
 ** Written by @JoeFachrizal 10/09/2020 08.52
 **/

class ListSurveyAdapter(private var dataList: ArrayList<FormSurveyor>) :
    RecyclerView.Adapter<ListSurveyAdapter.ViewHolder>(), Filterable {
    val userListFull: ArrayList<FormSurveyor> = arrayListOf()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemDataHasilSurveyBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(var binding: ItemDataHasilSurveyBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(dataSurvey: FormSurveyor) {
            with(binding) {
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

    override fun getFilter(): Filter? {
        return myFilter
    }

    private var myFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filteredList: ArrayList<FormSurveyor> = arrayListOf()
            if (charSequence.isEmpty()) {
                filteredList.addAll(userListFull)
            } else {
                for (data in userListFull) {
                    if (data.namaLengkap?.toLowerCase(Locale.getDefault())
                            ?.contains(charSequence.toString().toLowerCase(Locale.getDefault()))!!
                    ) {
                        filteredList.add(data)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        //Automatic on UI thread
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            dataList.clear()
            dataList.addAll((filterResults.values as Collection<FormSurveyor>))
            notifyDataSetChanged()
        }
    }

    init {
        userListFull.addAll(dataList)
    }
}