package com.example.soccermatch.ui.team

import androidx.lifecycle.ViewModel
import com.example.soccermatch.data.SoccerRepository

class TeamViewModel (private val soccerRepository: SoccerRepository): ViewModel(){

    fun searchTeam(dataSearch:String) = soccerRepository.searchTeams(dataSearch)

}