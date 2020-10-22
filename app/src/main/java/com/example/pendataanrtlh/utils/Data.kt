package com.example.pendataanrtlh.utils

import android.net.Uri

/**
 ** Written by @JoeFachrizal 29/08/2020 00.14
 **/
object Data {
    const val REGISTER_FORM = "registerForm"
    const val USER_DATA = "userData"

    const val DATA_SURVEY = "DataSurvey"
    const val UPLOAD_FOTO = "uploadFoto"
    const val DATA_SURVEYOR = "DataSurveyor"

    //Fragment One
    var nomorRumah: String? = null
    var namaLengkap: String? = null
    var usia: String? = null
    var pendidikan: String? = null
    var jenisKelamin: String? = null
    var almLengkp: String? = null
    var titikKoordinat: String? = null
    var noKTPUser: String? = null
    var jumlhKK: String? = null
    var pekerjaan: String? = null
    var penghasilan: String? = null
    var statusTanah: String? = null
    var statusRumah: String? = null
    var assetRumah: String? = null
    var assetTanah: String? = null
    var bantuanRumah: String? = null
    var kawasanLokasi: String? = null

    //Fragment Two
    var pondasi: String? = null
    var balok: String? = null
    var atap: String? = null

    //Fragment Tree
    var jendela: String? = null
    var ventilasi: String? = null
    var kmrMandi: String? = null
    var jrkKmrMandi: String? = null
    var sumAirMinum: String? = null
    var sumListrik: String? = null

    //Fragment Four
    var luasRumah: String? = null
    var jumPenghuni: String? = null

    //Fragment Five
    var matAtap: String? = null
    var konAtap: String? = null
    var matDinding: String? = null
    var konDinding: String? = null
    var matLantai: String? = null
    var konLantai: String? = null

    // Fragment Six
    var nameDesaKel: String? = null
    var nameKec: String? = null
    var nameKotaKab: String? = null
    var nameProv: String? = null

    var noKTPSurveyor: String? = null
    var fullName: String? = null
    var tglInput: String? = null

    var imgPath: Uri? = null
    var imgKondisiKolom: Uri? = null
    var imgKondisiAtap: Uri? = null

    var img1: Uri? = null
    var img2: Uri? = null
    var img3: Uri? = null

}