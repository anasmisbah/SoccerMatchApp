package com.example.soccermatch.data.local.entity


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(val id: Int,
                  val name:String,
                  val badge:Int? = null,
                  val description: String? = null,
                  val logo: String?= null,
                  val trophy: String?= null,
                  val banner: String?= null,
                  val since: String?= null) : Parcelable