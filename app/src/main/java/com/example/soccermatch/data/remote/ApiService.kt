package com.example.soccermatch.data.remote

import com.example.soccermatch.data.remote.response.DetailLeagueResponse
import com.example.soccermatch.data.remote.response.DetailTeamResponse
import com.example.soccermatch.data.remote.response.MatchLeagueResponse
import com.example.soccermatch.data.remote.response.SearchMatchResponse
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
}