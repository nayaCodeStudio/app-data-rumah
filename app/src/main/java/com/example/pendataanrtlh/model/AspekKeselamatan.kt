package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 23/08/2020 21.51
 **/
@Parcelize
class AspekKeselamatan(
    var pondasi: String? = null,
    var kondisiBalok: String? = null,
    var kondisiKontrkAtap: String? = null,
) : Parcelable