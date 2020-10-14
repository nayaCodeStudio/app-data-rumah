package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 25/08/2020 10.25
 **/
@Parcelize
class FormDataSurvey (

    //Fragment One
    var nomorRumah: String? = null,
    var namaLengkap: String? = null,
    var usia: String? = null,
    var pendidikan: String? = null,
    var jenisKelamin: String? = null,
    var titikKoordinat: String? = null,
    var almLengkp: String? = null,
    var noKTP: String? = null,
    var jumlhKK: String? = null,
    var pekerjaan: String? = null,
    var penghasilan: String? = null,
    var statusTanah: String? = null,
    var statusRumah: String? = null,
    var assetRumah: String? = null,
    var assetTanah: String? = null,
    var bantuanRumah: String? = null,
    var kawasanLokasi: String? = null,

    //Fragment Two
    var pondasi: String? = null,
    var balok: String? = null,
    var atap: String? = null,

    //Fragment Tree
    var jendela: String? = null,
    var ventilasi: String? = null,
    var kmrMandi: String? = null,
    var jrkKmrMandi: String? = null,
    var sumAirMinum: String? = null,
    var sumListrik: String? = null,

    //Fragment Four
    var luasRumah: String? = null,
    var jumPenghuni: String? = null,

    //Fragment Five
    var matAtap: String? = null,
    var konAtap: String? = null,
    var matDinding: String? = null,
    var konDinding: String? = null,
    var matLantai: String? = null,
    var konLantai: String? = null,

    // Fragment Six
    var nameDesaKel: String? = null,
    var nameKec: String? = null,
    var nameKotaKab: String? = null,
    var nameProv: String? = null,
):Parcelable