package com.example.soccermatch.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccermatch.data.UiState
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Standings
import com.example.soccermatch.data.local.entity.Team
import com.example.soccermatch.data.remote.response.*
import com.example.soccermatch.utils.DataDummy
import com.example.soccermatch.utils.EspressoIdlingResource
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
                    it.leagues?.let {item->
                        val result = item[0]
                        league.value = League(result.idLeague.toInt(),result.strNaming,null,result.strDescriptionEN,result.strLogo,result.strTrophy,result.strBanner,result.intFormedYear)
                    }

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
                        it.events?.let {events->
                            matches.value =  events.map {event->
                                Match(event.dateEvent,event.idAwayTeam,event.idEvent,event.idHomeTeam,event.intAwayScore,event.intHomeScore,event.strEvent,event.strAwayTeam,event.strHomeTeam)
                            }
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
                        it.events?.let { events ->
                            matches.value =  events.map {event->
                                Match(event.dateEvent,event.idAwayTeam,event.idEvent,event.idHomeTeam,event.intAwayScore,event.intHomeScore,event.strEvent,event.strAwayTeam,event.strHomeTeam)
                            }
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

                        it.events?.let {events->
                            val result = events[0]
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

                        it.teams?.let {list->
                            val result = list[0]
                            team.value = Team(result.strTeamBadge?:"",result.strTeamBadge?:"",result.strCountry,result.strDescriptionEN?:"",
                                result.strFacebook?:"",result.strGender,result.strInstagram,result.strStadiumThumb,result.strTeam,result.strTeamBanner,result.strTeamFanart1,
                                result.strTeamFanart2,result.strTeamFanart3,result.strTeamFanart4,result.strTeamJersey,result.strTeamLogo,result.strYoutube?:"",result.strTwitter?:"")
                        }
                    }
                }
            }
        )
        return team
    }

    fun searchMatch(dataSearch:String): LiveData<List<Match>>{
        val matches = MutableLiveData<List<Match>>()
        EspressoIdlingResource.increment()
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
                    EspressoIdlingResource.decrement()
                }

            }
        )
        return matches
    }

    fun getLeagueStandings(id: Int):LiveData<UiState<List<Standings>>>{
        val standings = MutableLiveData<UiState<List<Standings>>>()
        standings.value = UiState.Loading(true)
        apiClient.getLeagueStandings("$id").enqueue(
            object : Callback<StandingsResponse>{
                override fun onFailure(call: Call<StandingsResponse>, t: Throwable) {
                    standings.value = UiState.Error(t)
                    Log.d("getLeagueStandings",t.toString())
                }

                override fun onResponse(
                    call: Call<StandingsResponse>,
                    response: Response<StandingsResponse>
                ) {
                    response.body()?.let {
                        val result = it.table
                        val mapResult = result.map { standings->
                            Standings(standings.draw,standings.goalsfor,standings.loss,standings.name,standings.played,standings.teamid,standings.total,standings.win)
                        }
                        Log.d("getLeagueStandings","disini")
                        standings.value = UiState.Success(mapResult)
                    }
                }

            }
        )

        return standings
    }

    fun getLeagueTeams(id:Int):LiveData<UiState<List<Team>>>{
        val teams = MutableLiveData<UiState<List<Team>>>()
        teams.value = UiState.Loading(true)
        apiClient.getLeagueTeams("$id").enqueue(
            object : Callback<DetailTeamResponse>{
                override fun onFailure(call: Call<DetailTeamResponse>, t: Throwable) {
                    teams.value = UiState.Error(t)
                    Log.d("getLeagueTeams",t.toString())
                }
                override fun onResponse(
                    call: Call<DetailTeamResponse>,
                    response: Response<DetailTeamResponse>
                ) {
                    response.body()?.let {
                        it.teams?.let {list->
                            val mapResult = list.map { result->
                                Team(result.strTeamBadge?:"",result.strTeamBadge?:"",result.strCountry,result.strDescriptionEN?:"",result.strFacebook?:"",result.strGender,result.strInstagram,
                                    result.strStadiumThumb,result.strTeam,result.strTeamBanner,result.strTeamFanart1,result.strTeamFanart2,result.strTeamFanart3,
                                    result.strTeamFanart4,result.strTeamJersey,result.strTeamLogo,result.strYoutube?:"",result.strTwitter?:"")
                            }
                            teams.value = UiState.Success(mapResult)
                        }
                    }
                }
            }
        )
        return teams
    }

    fun searchTeams(dataSearch:String):LiveData<UiState<List<Team>>>{
        val teams = MutableLiveData<UiState<List<Team>>>()
        teams.value = UiState.Loading(true)
        apiClient.searchTeam(dataSearch).enqueue(
            object : Callback<DetailTeamResponse>{
                override fun onFailure(call: Call<DetailTeamResponse>, t: Throwable) {
                    teams.value = UiState.Error(t)
                    Log.d("searchTeams",t.toString())
                }
                override fun onResponse(
                    call: Call<DetailTeamResponse>,
                    response: Response<DetailTeamResponse>
                ) {
                    response.body()?.let {
                        var mapResult = emptyList<Team>()
                        it.teams?.let {list ->
                             mapResult = list.map { result->
                                Team(result.strTeamBadge?:"",result.strTeamBadge?:"",result.strCountry,result.strDescriptionEN?:"",result.strFacebook?:"",result.strGender,result.strInstagram,
                                    result.strStadiumThumb,result.strTeam,result.strTeamBanner,result.strTeamFanart1,result.strTeamFanart2,result.strTeamFanart3,
                                    result.strTeamFanart4,result.strTeamJersey,result.strTeamLogo,result.strYoutube?:"",result.strTwitter?:"")
                            }

                        }
                        teams.value = UiState.Success(mapResult)
                    }
                }
            }
        )
        return teams
    }
}