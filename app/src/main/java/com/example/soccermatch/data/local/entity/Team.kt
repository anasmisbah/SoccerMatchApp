package com.example.soccermatch.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val strTeamBadge: String
): Parcelable