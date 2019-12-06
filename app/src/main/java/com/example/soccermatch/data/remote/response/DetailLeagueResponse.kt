package com.example.soccermatch.data.remote.response

data class DetailLeagueResponse(
    val leagues: List<LeagueResponse>?
)

data class LeagueResponse(
    val idLeague: String,
    val intFormedYear: String,
    val strBadge: String,
    val strBanner: String,
    val strDescriptionEN: String,
    val strLeague: String,
    val strLogo: String,
    val strTrophy: String,
    val strNaming:String
)