package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 25/08/2020 10.25
 **/
@Parcelize
class AspekBangunan (
    var matrialAtap: String? = null,
    var kondisiAtap: String? = null,
    var matrialDinding: String? = null,
    var kondisiDinding: String? = null,
    var matrialLantai: String? = null,
    var kondisiLantai: String? = null,
):Parcelable