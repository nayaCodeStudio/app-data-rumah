package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 29/08/2020 00.10
 **/
@Parcelize
class FormSurveyor(
    var namaLengkap: String? = null,
    var noKTPPeserta: String? = null,
    var nameSurveyor: String? = null,
    var noKTPSurveyor: String? = null,
    var tglInput: String? = null,
) : Parcelable
