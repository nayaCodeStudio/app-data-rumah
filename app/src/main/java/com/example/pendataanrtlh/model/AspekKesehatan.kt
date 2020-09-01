package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 23/08/2020 21.51
 **/
@Parcelize
class AspekKesehatan(
    var jendela: String? = null,
    var ventilasi: String? = null,
    var kamarMandi: String? = null,
    var jarakKamarMandi: String? = null,
    var sumberAirMinum: String? = null,
    var sumberListrik: String? = null,
    var luasRumah: String? = null,
    var jumPenghuni: String? = null,
) : Parcelable