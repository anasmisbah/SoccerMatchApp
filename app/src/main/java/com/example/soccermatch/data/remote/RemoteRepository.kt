package com.example.soccermatch.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Team
import com.example.soccermatch.data.remote.response.*
import com.example.soccermatch.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiClient = ApiClient.provideApiClient()

    companion object{
        private var remoteRepository : RemoteRepository? = null

        val INSTANCE : RemoteRepository
            get() {
                if (remoteRepository == null) remoteRepository = RemoteRepository()

                return remoteRepository!!
            }
    }

    fun getAllLeagues(): LiveData<ArrayList<League>>{
        val leagues = MutableLiveData<ArrayList<League>>()

        leagues.value = DataDummy.generateDummyLeague()

        return leagues
    }

    fun getDetailLeague(leagueId:Int): LiveData<League>{
        val league = MutableLiveData<League>()

        apiClient.getDetailLeague("$leagueId").enqueue(object : Callback<DetailLeagueResponse>{
            override fun onFailure(call: Call<DetailLeagueResponse>, t: Throwable) {
                Log.e("getDetailLeague"," error : ${t.message}")
            }

            override fun onResponse(
                call: Call<DetailLeagueResponse>,
                response: Response<DetailLeagueResponse>
            ) {

                response.body()?.let {
                    val result = it.leagues[0]
                    league.value = League(result.idLeague.toInt(),result.strNaming,null,result.strDescriptionEN,result.strLogo,result.strTrophy,result.strBanner,result.intFormedYear)
                }
            }

        })

        return league

    }

    fun getLeaguePreviousEvent(id: Int): LiveData<List<Match>>{
        val matches = MutableLiveData<List<Match>>()

        apiClient.getLeaguePreviousEvent("$id").enqueue(
            object : Callback<MatchLeagueResponse>{
                override fun onFailure(call: Call<MatchLeagueResponse>, t: Throwable) {
                    Log.e("getLeaguePreviousEvent"," error : ${t.message}")
                }

                override fun onResponse(
                    call: Call<MatchLeagueResponse>,
                    response: Response<MatchLeagueResponse>
                ) {
                    response.body()?.let {
                        val result = it.events

                         matches.value =  result.map {event->
                            Match(event.dateEvent,event.idAwayTeam,event.idEvent,event.idHomeTeam,event.intAwayScore,event.intHomeScore,event.strEvent,event.strAwayTeam,event.strHomeTeam)
                        }
                    }
                }

            }
        )

        return matches
    }

    fun getLeagueNextEvent(id:Int): LiveData<List<Match>> {
        val matches = MutableLiveData<List<Match>>()

        apiClient.getLeagueNextEvent("$id").enqueue(
            object : Callback<MatchLeagueResponse>{
                override fun onFailure(call: Call<MatchLeagueResponse>, t: Throwable) {
                    Log.e("getLeagueNextEvent"," error : ${t.message}")
                }

                override fun onResponse(
                    call: Call<MatchLeagueResponse>,
                    response: Response<MatchLeagueResponse>
                ) {
                    response.body()?.let {
                        val result = it.events

                        matches.value =  result.map {event->
                            Match(event.dateEvent,event.idAwayTeam,event.idEvent,event.idHomeTeam,event.intAwayScore,event.intHomeScore,event.strEvent,event.strAwayTeam,event.strHomeTeam)
                        }
                    }
                }

            }
        )

        return matches
    }

    fun getDetailMatch(matchId:String): LiveData<Match>{
        val match = MutableLiveData<Match>()

        apiClient.getDetailMatch(matchId).enqueue(
            object : Callback<MatchLeagueResponse>{
                override fun onFailure(call: Call<MatchLeagueResponse>, t: Throwable) {
                    Log.e("getDetailMatch"," error : ${t.message}")
                }

                override fun onResponse(
                    call: Call<MatchLeagueResponse>,
                    response: Response<MatchLeagueResponse>
                ) {
                    response.body()?.let {
                        val result = it.events[0]
                        match.value = Match(
                            result.dateEvent,result.idAwayTeam,
                            result.idEvent,result.idHomeTeam,result.intAwayScore,
                            result.intHomeScore,result.strEvent,
                            result.strAwayTeam,result.strHomeTeam?:"-",result.strAwayGoalDetails?:"-",
                            result.strAwayRedCards?:"-",result.strAwayYellowCards?:"-",result.strAwayLineupForward?:"-",
                            result.strAwayLineupGoalkeeper?:"-",
                            "${result.strAwayFormation ?:"-"}","${result.strHomeFormation?:"-"}",
                            result.strHomeGoalDetails?:"-",result.strHomeLineupForward?:"-",
                            result.strHomeLineupGoalkeeper?:"-",result.strHomeRedCards?:"-",
                            result.strHomeYellowCards?:"-")
                    }
                }
            }
        )

        return match

    }

    fun getDetailTeam(teamId:String): LiveData<Team>{
        val team = MutableLiveData<Team>()

        apiClient.getDetailTeam(teamId).enqueue(
            object : Callback<DetailTeamResponse>{
                override fun onFailure(call: Call<DetailTeamResponse>, t: Throwable) {
                    Log.e("getDetailTeam"," error : ${t.message}")
                }

                override fun onResponse(
                    call: Call<DetailTeamResponse>,
                    response: Response<DetailTeamResponse>
                ) {
                    response.body()?.let {
                        val result = it.teams[0]
                        team.value = Team(result.strTeamBadge)
                    }
                }
            }
        )
        return team
    }

    fun searchMatch(dataSearch:String): LiveData<List<Match>>{
        val matches = MutableLiveData<List<Match>>()

        apiClient.searchMatch(dataSearch).enqueue(
            object : Callback<SearchMatchResponse>{
                override fun onFailure(call: Call<SearchMatchResponse>, t: Throwable) {
                    Log.e("searchMatch"," error : ${t.message}")
                }

                override fun onResponse(
                    call: Call<SearchMatchResponse>,
                    response: Response<SearchMatchResponse>
                ) {
                    response.body()?.let {
                        val result = it.event
                        matches.value =  result.map {event->
                            Match(event.dateEvent,event.idAwayTeam,event.idEvent,event.idHomeTeam,event.intAwayScore,event.intHomeScore,event.strEvent,event.strAwayTeam,event.strHomeTeam)
                        }
                    }
                }

            }
        )
        return matches
    }
}