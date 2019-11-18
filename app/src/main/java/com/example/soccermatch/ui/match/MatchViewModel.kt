package com.example.soccermatch.ui.match

import androidx.lifecycle.ViewModel
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.Match

class MatchViewModel (private val soccerRepository: SoccerRepository):ViewModel(){

    fun getDetailMatch(matchId:String) = soccerRepository.getDetailMatch(matchId)

    fun getDetailAwayTeam(teamId:String) = soccerRepository.getDetailTeam(teamId)

    fun getDetailHomeTeam(teamId: String) = soccerRepository.getDetailTeam(teamId)

    fun searchMatch(dataSearch:String) = soccerRepository.searchMatch(dataSearch)

    fun addFavorite(match:Match)=soccerRepository.addFavoriteMatch(match)

    fun removeFavorite(match: Match)=soccerRepository.removeFavoriteMatch(match)

    fun favoriteState(match: Match) = soccerRepository.favoriteState(match)
}