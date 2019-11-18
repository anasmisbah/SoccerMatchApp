package com.example.soccermatch.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match

class DetailLeagueViewModel (private val soccerRepository: SoccerRepository) : ViewModel(){


    fun getDetailLeague(leagueId:Int):LiveData<League>{
        return  soccerRepository.getDetailLeague(leagueId)
    }

    fun getNextMatch(leagueId: Int) : LiveData<List<Match>> = soccerRepository.getLeagueNextEvent(leagueId)

    fun getPreviousMatch(leagueId: Int):LiveData<List<Match>> = soccerRepository.getLeaguePreviousEvent(leagueId)
}