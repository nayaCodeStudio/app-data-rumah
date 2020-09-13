package com.example.pendataanrtlh.surveyresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.adapter.ListSurveyAdapter
import com.example.pendataanrtlh.databinding.ActivityHasilSurveyBinding
import com.example.pendataanrtlh.model.FormSurveyor
import com.example.pendataanrtlh.utils.Data
import com.google.firebase.database.*

class HasilSurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilSurveyBinding
    private lateinit var listSurveyAdapter: ListSurveyAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()

        initData()
        onGetData()
    }

    private fun initData() {
        val animationType = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(this, animationType)
        binding.rvListSurvey.layoutAnimation = animation

        binding.rvListSurvey.layoutManager = LinearLayoutManager(this)
        binding.rvListSurvey.adapter?.notifyDataSetChanged()
        binding.rvListSurvey.scheduleLayoutAnimation()
        binding.rvListSurvey.setHasFixedSize(true)
    }

    private fun onGetData() {
        myRef = database.getReference(Data.DATA_SURVEYOR)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataSurvey: ArrayList<FormSurveyor> = arrayListOf()
                for (dataSnapshot1 in snapshot.children) {
                    val dataReseller = dataSnapshot1.getValue(FormSurveyor::class.java)
                    dataReseller?.let { dataSurvey.add(it) }

                }
                onShowData(dataSurvey)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun onShowData(listData : ArrayList<FormSurveyor>) {
        listSurveyAdapter = ListSurveyAdapter(listData)
        binding.rvListSurvey.adapter = listSurveyAdapter
    }

}