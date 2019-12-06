package com.example.soccermatch.data.remote.response

data class DetailTeamResponse(
    val teams: List<TeamResponse>?
)

data class TeamResponse(
    val idLeague: String,
    val idSoccerXML: String,
    val idTeam: String,
    val intFormedYear: String?,
    val strCountry: String,
    val strDescriptionDE: String?,
    val strDescriptionEN: String?,
    val strFacebook: String?,
    val strGender: String,
    val strInstagram: String?,
    val strLeague: String,
    val strStadiumThumb: String?,
    val strTeam: String,
    val strTeamBadge: String?,
    val strTeamBanner: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTwitter: String?,
    val strYoutube: String?
)