package com.example.soccermatch.ui.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.utils.FakeDataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeagueViewModelTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val dummyLeague = FakeDataDummy.generateDummyLeague()[0]

    @Mock
    lateinit var soccerRepository: SoccerRepository

    lateinit var detailLeagueViewModel: DetailLeagueViewModel

    @Mock
    lateinit var observerDetailLeague: Observer<League>

    @Mock
    lateinit var observerMatch : Observer<List<Match>>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        detailLeagueViewModel = DetailLeagueViewModel(soccerRepository)
    }

    @Test
    fun getDetailLeague(){
        val league = MutableLiveData<League>()
        league.value = dummyLeague

        Mockito.`when`(soccerRepository.getDetailLeague(dummyLeague.id)).thenReturn(league)

        detailLeagueViewModel.getDetailLeague(dummyLeague.id).observeForever(observerDetailLeague)
        Mockito.verify(observerDetailLeague).onChanged(dummyLeague)

    }

    @Test
    fun getNextMatch(){
        val dummyNextMatch = FakeDataDummy.generateListMatch()
        val matches = MutableLiveData<List<Match>>()
        matches.value = dummyNextMatch

        Mockito.`when`(soccerRepository.getLeagueNextEvent(dummyLeague.id)).thenReturn(matches)

        detailLeagueViewModel.getNextMatch(dummyLeague.id).observeForever(observerMatch)
        Mockito.verify(observerMatch).onChanged(dummyNextMatch)
    }

    @Test
    fun getPreviousMatch(){
        val dummyPreviousMatch = FakeDataDummy.generateListMatch()
        val matches = MutableLiveData<List<Match>>()
        matches.value = dummyPreviousMatch

        Mockito.`when`(soccerRepository.getLeaguePreviousEvent(dummyLeague.id)).thenReturn(matches)

        detailLeagueViewModel.getPreviousMatch(dummyLeague.id).observeForever(observerMatch)
        Mockito.verify(observerMatch).onChanged(dummyPreviousMatch)
    }


}