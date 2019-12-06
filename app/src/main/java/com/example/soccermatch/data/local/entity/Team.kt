package com.example.soccermatch.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val idTeam: String,
    val strTeamBadge: String,
    val strCountry: String,
    val strDescriptionEN: String,
    val strFacebook: String,
    val strGender: String,
    val strInstagram: String? =null,
    val strStadiumThumb: String? =null,
    val strTeam: String,
    val strTeamBanner: String? = null,
    val strTeamFanart1: String? =null,
    val strTeamFanart2: String? =null,
    val strTeamFanart3: String? =null,
    val strTeamFanart4: String? =null,
    val strTeamJersey: String? =null,
    val strTeamLogo: String? =null,
    val strYoutube: String,
    val strTwitter: String
): Parcelable