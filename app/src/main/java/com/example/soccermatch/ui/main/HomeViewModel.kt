package com.example.soccermatch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match

class HomeViewModel(private val soccerRepository: SoccerRepository):ViewModel(){


    fun getLeagues(): LiveData<ArrayList<League>>{
        return soccerRepository.getLeagues()
    }

    fun getFavoriteMatches():LiveData<List<Match>> = soccerRepository.getFavorite()


}