package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 23/08/2020 21.34
 **/
@Parcelize
data class IdentitasRumah(
    var noUrutRumah: String? = null,
    var nmLengkap: String? = null,
    var tahun: String? = null,
    var pendTerakhir: String? = null,
    var jnsKelamin: String? = null,
    var almtLengkap: String? = null,
    var noNIK: String? = null,
    var jumKK: String? = null,
    var pekerjaan: String? = null,
    var penghslDpngluarnPerbln: String? = null,
    var stsKepemilikan: String? = null,
    var astRumahDitmpatLain: String? = null,
    var astTanahDitmpatLain: String? = null,
    var prnhMendapatkanBantPermhan: String? = null,
    var jnsKawasanLokRmhDitmpti: String? = null,
) : Parcelable

