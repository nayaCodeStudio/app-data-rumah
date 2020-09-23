package com.example.pendataanrtlh.surveyresult

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.adapter.ListSurveyAdapter
import com.example.pendataanrtlh.databinding.ActivityHasilSurveyBinding
import com.example.pendataanrtlh.model.FormSurveyor
import com.example.pendataanrtlh.utils.Data
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class HasilSurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilSurveyBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    lateinit var listSurveyAdapter: ListSurveyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        setSupportActionBar(binding.toolbar)

        initData()
        onGetData()
    }

    private fun initData() {
        val animationType = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(this, animationType)
        with(binding) {
            rvListSurvey.layoutAnimation = animation
            rvListSurvey.adapter?.notifyDataSetChanged()
            rvListSurvey.scheduleLayoutAnimation()
            rvListSurvey.setHasFixedSize(true)
        }
    }

    private fun onGetData() {
        myRef = database.getReference(Data.DATA_SURVEYOR)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataSurvey: ArrayList<FormSurveyor> = arrayListOf()
                for (dataSnapshot1 in snapshot.children) {
                    val surveyor = dataSnapshot1.getValue(FormSurveyor::class.java)
                    surveyor?.let { dataSurvey.add(it) }
                }
                onShowData(dataSurvey)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun onShowData(listData: ArrayList<FormSurveyor>) {
        listSurveyAdapter = ListSurveyAdapter(listData)
        binding.rvListSurvey.adapter = listSurveyAdapter

        listSurveyAdapter.setOnItemClickCallback(object : ListSurveyAdapter.OnItemClickCallback {
            override fun onClicked(data: FormSurveyor) {
                val intent = Intent(this@HasilSurveyActivity, DetailHasilSurveyActivity::class.java)
                intent.putExtra(DetailHasilSurveyActivity.NAMA_USER, data.namaLengkap)
                intent.putExtra(DetailHasilSurveyActivity.NIK_USER, data.noKTPPeserta)
                intent.putExtra(DetailHasilSurveyActivity.NAMA_SURVEYOR, data.nameSurveyor)
                intent.putExtra(DetailHasilSurveyActivity.NIK_SURVEYOR, data.noKTPSurveyor)
                intent.putExtra(DetailHasilSurveyActivity.TGL_INPUT, data.tglInput)
                startActivity(intent)
            }

        })
    }

    private fun onShowTime() {
        val thread = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            val date = System.currentTimeMillis()
                            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                            val dateString = sdf.format(date)
//                            Data.tglInput = dateString
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }
        thread.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val itemMenu = menu?.findItem(R.id.action_search) as MenuItem
        val searchView = itemMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    listSurveyAdapter.filter?.filter(newText)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}