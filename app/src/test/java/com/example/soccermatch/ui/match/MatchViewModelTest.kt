package com.example.soccermatch.ui.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.soccermatch.data.SoccerRepository
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Team
import com.example.soccermatch.utils.FakeDataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchViewModelTest{

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var soccerRepository: SoccerRepository

    lateinit var matchViewModel: MatchViewModel

    private val dummyMatches = FakeDataDummy.generateListMatch()
    private val dummyMatch = FakeDataDummy.generateListMatch()[0]
    private val dummyTeam = FakeDataDummy.generateTeam()

    @Mock
    lateinit var observerMatch: Observer<Match>

    @Mock
    lateinit var observerTeam : Observer<Team>

    @Mock
    lateinit var observerSearchMatch : Observer<List<Match>>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        matchViewModel = MatchViewModel(soccerRepository)
    }

    @Test
    fun getDetailMatch(){

        val match = MutableLiveData<Match>()
        match.value = dummyMatch

        Mockito.`when`(soccerRepository.getDetailMatch(dummyMatch.idEvent?:"")).thenReturn(match)

        matchViewModel.getDetailMatch(dummyMatch.idEvent?:"").observeForever(observerMatch)

        Mockito.verify(observerMatch).onChanged(dummyMatch)

    }

    @Test
    fun getDetailAwayTeam(){
        val team = MutableLiveData<Team>()
        team.value = dummyTeam

        Mockito.`when`(soccerRepository.getDetailTeam(dummyMatch.idAwayTeam?:"")).thenReturn(team)

        matchViewModel.getDetailAwayTeam(dummyMatch.idAwayTeam?:"").observeForever(observerTeam)

        Mockito.verify(observerTeam).onChanged(dummyTeam)
    }

    @Test
    fun getDetailHomeTeam(){
        val team = MutableLiveData<Team>()
        team.value = dummyTeam

        Mockito.`when`(soccerRepository.getDetailTeam(dummyMatch.idHomeTeam?:"")).thenReturn(team)

        matchViewModel.getDetailHomeTeam(dummyMatch.idHomeTeam?:"").observeForever(observerTeam)

        Mockito.verify(observerTeam).onChanged(dummyTeam)
    }

    @Test
    fun searchMatch(){

        val key = "liverpool"

        val searchedMatches = MutableLiveData<List<Match>>()
        searchedMatches.value = dummyMatches

        Mockito.`when`(soccerRepository.searchMatch(key)).thenReturn(searchedMatches)

        matchViewModel.searchMatch(key).observeForever(observerSearchMatch)

        Mockito.verify(observerSearchMatch).onChanged(dummyMatches)

    }

    @Test
    fun addFavorite(){

        Mockito.`when`(soccerRepository.addFavoriteMatch(dummyMatch)).thenReturn(true)

        val result = matchViewModel.addFavorite(dummyMatch)
        assertEquals(true,result)
    }

    @Test
    fun removeFavorite(){
        Mockito.`when`(soccerRepository.removeFavoriteMatch(dummyMatch)).thenReturn(true)

        val result = matchViewModel.removeFavorite(dummyMatch)
        assertEquals(true,result)
    }

    @Test
    fun favoriteState() {
        Mockito.`when`(soccerRepository.favoriteState(dummyMatch)).thenReturn(true)

        val result = matchViewModel.favoriteState(dummyMatch)
        assertEquals(true,result)

    }

}