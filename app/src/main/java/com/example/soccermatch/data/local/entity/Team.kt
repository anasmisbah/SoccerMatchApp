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
    val strTwitter: String,
    val id:Long?=null
): Parcelable{
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_COUNTRY: String = "TEAM_COUNTRY"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
        const val TEAM_FACEBOOK: String = "TEAM_FACEBOOK"
        const val TEAM_GENDER: String = "TEAM_GENDER"
        const val TEAM_INSTAGRAM: String = "TEAM_INSTAGRAM"
        const val TEAM_STADIUM_THUMB: String = "TEAM_STADIUM_THUMB"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BANNER: String = "TEAM_BANNER"
        const val TEAM_FANART1: String = "TEAM_FANART1"
        const val TEAM_FANART2: String = "TEAM_FANART2"
        const val TEAM_FANART3: String = "TEAM_FANART3"
        const val TEAM_FANART4: String = "TEAM_FANART4"
        const val TEAM_JERSEY: String = "TEAM_JERSEY"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_YOUTUBE: String = "TEAM_YOUTUBE"
        const val TEAM_TWITTER: String = "TEAM_TWITTER"

    }
}