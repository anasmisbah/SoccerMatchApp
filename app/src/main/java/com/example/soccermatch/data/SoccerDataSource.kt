package com.example.soccermatch.data

import androidx.lifecycle.LiveData
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Standings
import com.example.soccermatch.data.local.entity.Team

interface SoccerDataSource {


    //untuk halaman home league
    fun getLeagues() : LiveData<ArrayList<League>>

    //untuk halaman detail league
    fun getDetailLeague(leagueId:Int): LiveData<League>

    //untuk halaman previous match league
    fun getLeaguePreviousEvent(leagueId: Int): LiveData<List<Match>>

    //untuk halaman next match league
    fun getLeagueNextEvent(leagueId: Int): LiveData<List<Match>>

    //untuk halaman detail match league
    fun getDetailMatch(matchId:String): LiveData<Match>

    //untuk team bagde dihalaman detail match
    fun getDetailTeam(teamId:String): LiveData<Team>

    //untuk halaman search
    fun searchMatch(dataSearch:String):LiveData<List<Match>>

    //untuk halaman favorite
    fun getFavorite(): LiveData<List<Match>>

    //untuk halaman detail match, add favorite
    fun addFavoriteMatch(match: Match) : Boolean

    //untuk halaman detail match, remove
    fun removeFavoriteMatch(match: Match) : Boolean

    //untuk halaman detail match, check match favorite
    fun favoriteState(match: Match) : Boolean

    //untuk halaman klasemen
    fun getLeagueStandings(leagueId:Int):LiveData<UiState<List<Standings>>>

    //untuk halaman team league
    fun getLeagueTeams(leagueId: Int):LiveData<UiState<List<Team>>>

    //untuk halaman search team
    fun searchTeams(dataSearch:String):LiveData<UiState<List<Team>>>

    //untuk halaman favorite team
    fun getFavoriteTeams(): LiveData<UiState<List<Team>>>

    //untuk detail team, add favorite
    fun addFavoriteTeam(team: Team):Boolean

    //untuk detail team, remove favorite
    fun removeFavoriteTeam(team: Team):Boolean

    //untuk detail team, check favorite
    fun favoriteTeamState(team: Team):Boolean
}