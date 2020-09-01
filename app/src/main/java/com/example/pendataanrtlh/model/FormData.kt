package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormData(
    var noNIK: String? = null,
    var nameDesKel: String? = null,
    var nameKec: String? = null,
    var nameKabKot: String? = null,
    var nameProv: String? = null,
) : Parcelable
