package com.example.soccermatch.data.remote

import com.example.soccermatch.data.remote.response.*
import com.example.soccermatch.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ENDPOINT_DETAIL_LEAGUE)
    fun getDetailLeague(@Query("id") leagueId : String) : Call<DetailLeagueResponse>

    @GET(ENDPOINT_LEAGUE_PREVIOUS_EVENT)
    fun getLeaguePreviousEvent(@Query("id") leagueId: String) : Call<MatchLeagueResponse>

    @GET(ENDPOINT_LEAGUE_NEXT_EVENT)
    fun getLeagueNextEvent(@Query("id") leagueId: String) : Call<MatchLeagueResponse>

    @GET(ENDPOINT_DETAIL_MATCH)
    fun getDetailMatch(@Query("id") matchId:String) : Call<MatchLeagueResponse>

    @GET(ENDPOINT_SEARCH_MATCH)
    fun searchMatch(@Query("e") dataSearch : String) :Call<SearchMatchResponse>

    @GET(ENDPOINT_DETAIL_TEAM)
    fun getDetailTeam(@Query("id") teamId:String): Call<DetailTeamResponse>

    @GET(ENDPOINT_STANDINGS_LEAGUE)
    fun getLeagueStandings(@Query("l") leagueId: String): Call<StandingsResponse>

    @GET(ENDPOINT_TEAMS_LEAGUE)
    fun getLeagueTeams(@Query("id") leagueId: String):Call<DetailTeamResponse>

    @GET(ENDPOINT_SEARCH_TEAM)
    fun searchTeam(@Query("t") dataSearch : String) :Call<DetailTeamResponse>
}