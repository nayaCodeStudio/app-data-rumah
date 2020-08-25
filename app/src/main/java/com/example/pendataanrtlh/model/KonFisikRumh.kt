package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 23/08/2020 21.51
 **/
@Parcelize
class KonFisikRumh(
    var pondasi: String? = null,
    var kondisiBalok: String? = null,
    var kondisiKontrkAtap: String? = null,
    var jendela: String? = null,
    var ventilasi: String? = null,
    var kamarMandi: String? = null,
    var jarakKamarMandi: String? = null,
    var sumberAirMinum: String? = null,
    var sumberListrik: String? = null,
    ): Parcelable