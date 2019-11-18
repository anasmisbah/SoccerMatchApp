package com.example.soccermatch.di

import android.app.Application
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.MyDatabaseOpenHelper
import com.example.soccermatch.data.remote.RemoteRepository

object Injection {
    fun provideRepository(application: Application) : SoccerRepository{
        val remoteRepository = RemoteRepository.INSTANCE
        val myDatabaseOpenHelper = MyDatabaseOpenHelper(application)
        return  SoccerRepository.getInstance(remoteRepository,myDatabaseOpenHelper)
    }
}