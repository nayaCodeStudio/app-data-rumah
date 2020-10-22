package com.example.pendataanrtlh.surveyresult

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pendataanrtlh.databinding.ActivityDetailHasilSurveyBinding
import com.example.pendataanrtlh.model.FormDataSurvey
import com.example.pendataanrtlh.utils.Data
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.*

class DetailHasilSurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHasilSurveyBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    companion object {
        const val NAMA_USER = "nama_user"
        const val NIK_USER = "nik_user"
        const val TGL_INPUT = "tgl_input"
        const val NAMA_SURVEYOR = "nama_surveyor"
        const val NIK_SURVEYOR = "nik_surveyor"
        const val URL_IMAGE =
            "https://firebasestorage.googleapis.com/v0/b/kuisoneranri.appspot.com/o/gambar%2Fform3_1_5555.jpg?alt=media&token=34e3f818-9129-4e65-ae91-f43e6d59e4fe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHasilSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            /**Manggil Foto Kondisi Kolom**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiKolom1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiKolom2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiKolom3)

            /**Manggil Foto Kondisi Konstruksi Atap**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiKonstruksiAtap1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiKonstruksiAtap2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiKonstruksiAtap3)

            /**Manggil Foto Kondisi Atap**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiAtap1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiAtap2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiAtap3)

            /**Manggil Foto Kondisi Dinding**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiDinding1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiDinding2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiDinding3)

            /**Manggil Foto Kondisi Lantai**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiLantai1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiLantai2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load(URL_IMAGE)
                .into(fotoKondisiLantai3)

            fotoKondisiKolom1.setOnClickListener {
                dialogShow()
            }
        }

        database = FirebaseDatabase.getInstance()
        val namaUser = intent.getStringExtra(NAMA_USER).toString()
        val nikUser = intent.getStringExtra(NIK_USER).toString()
        val tgInput = intent.getStringExtra(TGL_INPUT).toString()
        val nmSurveyor = intent.getStringExtra(NAMA_SURVEYOR).toString()
        val nikSurveyor = intent.getStringExtra(NIK_SURVEYOR).toString()

        with(binding) {
            namaLengkap.text = namaUser
            nomorKTP.text = nikUser
            tglInput.text = tgInput
            namaSurveyor.text = nmSurveyor
        }

        onGetData(nikUser)

        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun onGetData(nikUser: String) {
        myRef = database.getReference("${Data.DATA_SURVEY}/$nikUser")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataSurvey = snapshot.getValue(FormDataSurvey::class.java)
                dataSurvey?.let { onShowData(it) }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun onShowData(dataSurvey: FormDataSurvey) {
        binding.nomorRumah.text = dataSurvey.nomorRumah
        binding.namaLengkapPenghuni.text = dataSurvey.namaLengkap
        binding.usia.text = dataSurvey.usia
        binding.pendidikanTerakhir.text = dataSurvey.pendidikan
        binding.jenisKelamin.text = dataSurvey.jenisKelamin
        binding.alamatLengkap.text = dataSurvey.almLengkp
        binding.titikKoordinat.text = dataSurvey.titikKoordinat
        binding.nomorKTPDetail.text = dataSurvey.noKTP
        binding.jumlahKK.text = dataSurvey.jumlhKK
        binding.pekerjaanUtama.text = dataSurvey.pekerjaan
        binding.penghasilanPengeluaran.text = dataSurvey.penghasilan
        binding.kepemilikanTanah.text = dataSurvey.statusTanah
        binding.kepemilikanRumah.text = dataSurvey.statusRumah
        binding.asetRumah.text = dataSurvey.assetRumah
        binding.asetTanah.text = dataSurvey.assetTanah
        binding.bantuanPerumahan.text = dataSurvey.bantuanRumah
        binding.jenisKawasanRumah.text = dataSurvey.kawasanLokasi
        binding.pondasi.text = dataSurvey.pondasi
        binding.kondisiKolom.text = dataSurvey.balok
        binding.kondisiKonstruksi.text = dataSurvey.atap
        binding.jendela.text = dataSurvey.jendela
        binding.ventilasi.text = dataSurvey.ventilasi
        binding.kepemilikanKamar.text = dataSurvey.kmrMandi
        binding.jarakSumberAirMinum.text = dataSurvey.jrkKmrMandi
        binding.sumberAirMinum.text = dataSurvey.sumAirMinum
        binding.sumberListrik.text = dataSurvey.sumListrik
        binding.luasRumah.text = dataSurvey.luasRumah
        binding.jumlahPenghuni.text = dataSurvey.jumPenghuni
        binding.materialAtap.text = dataSurvey.matAtap
        binding.kondisiAtap.text = dataSurvey.konAtap
        binding.materialDinding.text = dataSurvey.matDinding
        binding.kondisiDinding.text = dataSurvey.konDinding
        binding.materialLantai.text = dataSurvey.matLantai
        binding.kondisiLantai.text = dataSurvey.konLantai
    }

    private fun dialogShow(){
        MaterialAlertDialogBuilder(this)
            .setTitle("Title")
            .setMessage("Lorem")
//            .setView(
//                Glide.with(this@DetailHasilSurveyActivity)
//                    .load(URL_IMAGE)
//                    .into(fotoKondisiLantai3)
//            )

            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("Decline") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Accept") { dialog, which ->
                // Respond to positive button press
            }
            .show()
    }
}