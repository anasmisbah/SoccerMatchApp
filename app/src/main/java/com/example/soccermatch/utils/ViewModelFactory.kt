package com.example.soccermatch.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.di.Injection
import com.example.soccermatch.ui.league.DetailLeagueViewModel
import com.example.soccermatch.ui.main.HomeViewModel
import com.example.soccermatch.ui.match.MatchViewModel
import com.example.soccermatch.ui.team.TeamViewModel

class ViewModelFactory(private val soccerRepository: SoccerRepository): ViewModelProvider.NewInstanceFactory(){
    companion object{
        private var  INSTANCE : ViewModelFactory? = null
        fun getInstance(application: Application?) : ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(Injection.provideRepository(application!!))
                }
            }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(soccerRepository) as T
        }else if (modelClass.isAssignableFrom(DetailLeagueViewModel::class.java)){
            return DetailLeagueViewModel(soccerRepository) as T
        }else if (modelClass.isAssignableFrom(MatchViewModel::class.java)){
            return MatchViewModel(soccerRepository) as T
        }else if (modelClass.isAssignableFrom(TeamViewModel::class.java)){
            return TeamViewModel(soccerRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}