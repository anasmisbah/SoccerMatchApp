package com.example.soccermatch.utils

import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Team

object FakeDataDummy {
    fun generateDummyLeague() : ArrayList<League>{
        val leagues = ArrayList<League>()
        leagues.add(
            League(
                4328,
                "English Premier League",
                R.drawable.english_premier_league
            )
        )
        leagues.add(
            League(
                4334,
                "French Ligue 1",
                R.drawable.french_ligue_1
            )
        )
        leagues.add(
            League(
                4331,
                "German Bundesliga",
                R.drawable.german_bundesliga
            )
        )
        leagues.add(
            League(
                4332,
                "Italian Serie A",
                R.drawable.italian_serie_a
            )
        )
        leagues.add(
            League(
                4335,
                "Spanish La Liga",
                R.drawable.spanish_la_liga
            )
        )
        leagues.add(
            League(
                4346,
                "American Mayor League",
                R.drawable.american_mayor_league
            )
        )
        leagues.add(
            League(
                4344,
                "Protugeuese Premiera Liga",
                R.drawable.portugeuese_premiera_liga
            )
        )
        leagues.add(
            League(
                4356,
                "Australian A League",
                R.drawable.australian_a_league
            )
        )
        leagues.add(
            League(
                4330,
                "Scotish Premier League",
                R.drawable.scotish_premier_league
            )
        )
        leagues.add(
            League(
                4396,
                "English League 1",
                R.drawable.english_premier_league
            )
        )

        return leagues
    }

    fun generateDummyFavorite(): List<Match>{
        val favoriteMatch = mutableListOf<Match>()

        favoriteMatch.add(Match("12-11-97","123123","132111","765456","2","3","liverpool vs marcel","liverpool","marcel"))

        return favoriteMatch
    }

    fun generateListMatch():List<Match>{
        val matches = mutableListOf<Match>()

        matches.add(Match("12-11-97","123123","132111","765456","2","3","liverpool vs marcel","liverpool","marcel"))

        return matches
    }

    fun generateTeam(): Team {
        return Team("123123")
    }
}