package com.example.soccermatch.ui.team

import androidx.lifecycle.ViewModel
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.Team

class TeamViewModel (private val soccerRepository: SoccerRepository): ViewModel(){

    fun searchTeam(dataSearch:String) = soccerRepository.searchTeams(dataSearch)

    fun getFavoriteTeam() = soccerRepository.getFavoriteTeams()

    fun addFavorite(team: Team)=soccerRepository.addFavoriteTeam(team)

    fun removeFavorite(team: Team)=soccerRepository.removeFavoriteTeam(team)

    fun favoriteState(team: Team) = soccerRepository.favoriteTeamState(team)
}