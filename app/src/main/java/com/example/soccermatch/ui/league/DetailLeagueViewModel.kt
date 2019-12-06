package com.example.soccermatch.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.UiState
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Standings
import com.example.soccermatch.data.local.entity.Team

class DetailLeagueViewModel (private val soccerRepository: SoccerRepository) : ViewModel(){


    fun getDetailLeague(leagueId:Int):LiveData<League>{
        return  soccerRepository.getDetailLeague(leagueId)
    }

    fun getNextMatch(leagueId: Int) : LiveData<List<Match>> = soccerRepository.getLeagueNextEvent(leagueId)

    fun getPreviousMatch(leagueId: Int):LiveData<List<Match>> = soccerRepository.getLeaguePreviousEvent(leagueId)

    fun getLeagueStandings(leagueId: Int):LiveData<UiState<List<Standings>>> = soccerRepository.getLeagueStandings(leagueId)

    fun getLeagueTeams(leagueId: Int):LiveData<UiState<List<Team>>> = soccerRepository.getLeagueTeams(leagueId)
}