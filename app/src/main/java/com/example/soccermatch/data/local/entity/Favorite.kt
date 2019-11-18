package com.example.soccermatch.data.local.entity

data class Favorite(
                val id: Long?,
                val idEvent: String?,
                val dateEvent: String?,
                val nameEvent: String?,
                val idAwayTeam: String?,
                val strAwayTeam: String?,
                val intAwayScore: String?,
                val idHomeTeam: String?,
                val strHomeTeam: String?,
                val intHomeScore: String?){
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"

    }
}