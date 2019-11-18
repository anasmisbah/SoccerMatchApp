package com.example.soccermatch.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(val dateEvent: String?,
                 val idAwayTeam: String?,
                 val idEvent: String?,
                 val idHomeTeam: String?,
                 val intAwayScore: String?,
                 val intHomeScore: String?,
                 val nameEvent: String?,
                 val strAwayTeam: String?,
                 val strHomeTeam: String?,
                 val strAwayGoalDetails: String?= null,
                 val strAwayRedCards: String?= null,
                 val strAwayYellowCards: String?= null,
                 val strAwayLineupForward: String?= null,
                 val strAwayLineupGoalkeeper: String?= null,
                 val strAwayFormation: String?= null,
                 val strHomeFormation: String?= null,
                 val strHomeGoalDetails: String?= null,
                 val strHomeLineupForward: String?= null,
                 val strHomeLineupGoalkeeper: String?= null,
                 val strHomeRedCards: String?= null,
                 val strHomeYellowCards: String?= null
                 ) : Parcelable