package com.example.soccermatch.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.Favorite
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.utils.FakeDataDummy
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeViewModelTest{

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var soccerRepository: SoccerRepository

    lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var observer: Observer<ArrayList<League>>

    @Mock
    lateinit var observerFavorite: Observer<List<Match>>

     @Before
     fun setup(){
         MockitoAnnotations.initMocks(this)
         homeViewModel = HomeViewModel(soccerRepository)
     }

    @Test
    fun getLeagues(){
        val dummyLeague = FakeDataDummy.generateDummyLeague()

        val leagues = MutableLiveData<ArrayList<League>>()
        leagues.value = dummyLeague

        Mockito.`when`(soccerRepository.getLeagues()).thenReturn(leagues)

        homeViewModel.getLeagues().observeForever(observer)

        Mockito.verify(observer).onChanged(dummyLeague)
    }

    @Test
    fun getFavorites(){
        val dummyFavorite = FakeDataDummy.generateDummyFavorite()

        val favorites = MutableLiveData<List<Match>>()
        favorites.value = dummyFavorite

        Mockito.`when`(soccerRepository.getFavorite()).thenReturn(favorites)

        homeViewModel.getFavoriteMatches().observeForever(observerFavorite)
        Mockito.verify(observerFavorite).onChanged(dummyFavorite)
    }

}