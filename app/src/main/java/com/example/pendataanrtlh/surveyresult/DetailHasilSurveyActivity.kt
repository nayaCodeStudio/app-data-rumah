package com.example.pendataanrtlh.surveyresult

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.ActivityDetailHasilSurveyBinding
import com.example.pendataanrtlh.model.FormDataSurvey
import com.example.pendataanrtlh.utils.Data
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_view_foto.view.*

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
            "https://firebasestorage.googleapis.com/v0/b/kuisoneranri.appspot.com/o/gambar%2F"
        const val KEY_IMAGE = ".jpg?alt=media&token=34e3f818-9129-4e65-ae91-f43e6d59e4fe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHasilSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        onGetURLPhotos(nikUser)

        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun onGetURLPhotos(nikUser: String) {

        with(binding) {
            /**Manggil Foto Kondisi Kolom**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form3_1_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiKolom1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form3_2_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiKolom2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form3_3_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiKolom3)

            /**Manggil Foto Kondisi Konstruksi Atap**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form3_4_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiKonstruksiAtap1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form3_5_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiKonstruksiAtap2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form3_6_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiKonstruksiAtap3)

            /**Manggil Foto Kondisi Atap**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_1_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiAtap1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_2_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiAtap2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_3_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiAtap3)

            /**Manggil Foto Kondisi Dinding**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_4_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiDinding1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_5_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiDinding2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_6_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiDinding3)

            /**Manggil Foto Kondisi Lantai**/
            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_7_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiLantai1)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_8_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiLantai2)

            Glide.with(this@DetailHasilSurveyActivity)
                .load("${URL_IMAGE}form6_9_$nikUser$KEY_IMAGE")
                .placeholder(R.drawable.ic_add_photo)
                .into(fotoKondisiLantai3)
        }

        with(binding) {
            fotoKondisiKolom1.setOnClickListener {
                dialogShow("${URL_IMAGE}form3_1_$nikUser$KEY_IMAGE")
            }
            fotoKondisiKolom2.setOnClickListener {
                dialogShow("${URL_IMAGE}form3_2_$nikUser$KEY_IMAGE")
            }
            fotoKondisiKolom3.setOnClickListener {
                dialogShow("${URL_IMAGE}form3_3_$nikUser$KEY_IMAGE")
            }
            fotoKondisiKonstruksiAtap1.setOnClickListener {
                dialogShow("${URL_IMAGE}form3_4_$nikUser$KEY_IMAGE")
            }
            fotoKondisiKonstruksiAtap2.setOnClickListener {
                dialogShow("${URL_IMAGE}form3_5_$nikUser$KEY_IMAGE")
            }
            fotoKondisiKonstruksiAtap3.setOnClickListener {
                dialogShow("${URL_IMAGE}form3_6_$nikUser$KEY_IMAGE")
            }

            fotoKondisiAtap1.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_1_$nikUser$KEY_IMAGE")
            }
            fotoKondisiAtap2.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_2_$nikUser$KEY_IMAGE")
            }
            fotoKondisiAtap3.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_3_$nikUser$KEY_IMAGE")
            }
            fotoKondisiDinding1.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_4_$nikUser$KEY_IMAGE")
            }
            fotoKondisiDinding2.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_5_$nikUser$KEY_IMAGE")
            }
            fotoKondisiDinding3.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_6_$nikUser$KEY_IMAGE")
            }
            fotoKondisiLantai1.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_7_$nikUser$KEY_IMAGE")
            }
            fotoKondisiLantai2.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_8_$nikUser$KEY_IMAGE")
            }
            fotoKondisiLantai3.setOnClickListener {
                dialogShow("${URL_IMAGE}form6_9_$nikUser$KEY_IMAGE")
            }
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

    private fun dialogShow(imageURL: String) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.item_view_foto, null)

        Glide.with(this)
            .load(imageURL)
            .into(mDialogView.fotoView)

        MaterialAlertDialogBuilder(this)
            .setView(mDialogView)
            .show()
    }
}