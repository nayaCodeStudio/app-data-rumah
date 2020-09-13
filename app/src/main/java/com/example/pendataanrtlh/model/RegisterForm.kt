package com.example.pendataanrtlh.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by @JoeFachrizal 29/08/2020 00.10
 **/
@Parcelize
class RegisterForm(
    var noKTP: String? = null,
    var password: String? = null,
    var fullName: String? = null,
    var emailAddress: String? = null,
    var noHp: String? = null,
    var address: String? = null,
    var jenisKelamin: String? = null,
) : Parcelable
