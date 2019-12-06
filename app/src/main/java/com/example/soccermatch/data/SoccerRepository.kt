package com.example.soccermatch.data

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccermatch.data.local.MyDatabaseOpenHelper
import com.example.soccermatch.data.local.entity.*
import com.example.soccermatch.data.remote.RemoteRepository
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class SoccerRepository(
    private val remoteRepository: RemoteRepository,
    private val myDatabaseOpenHelper: MyDatabaseOpenHelper
) : SoccerDataSource{



    companion object{
        private var soccerRepository: SoccerRepository? = null

        fun getInstance(remoteRepository: RemoteRepository,myDatabaseOpenHelper: MyDatabaseOpenHelper) : SoccerRepository {
                if (soccerRepository == null) soccerRepository = SoccerRepository(remoteRepository,myDatabaseOpenHelper)

                return soccerRepository!!
            }
    }

    override fun getLeagues(): LiveData<ArrayList<League>> {
        return remoteRepository.getAllLeagues()
    }

    override fun getDetailLeague(leagueId: Int): LiveData<League> {
        return remoteRepository.getDetailLeague(leagueId)
    }

    override fun getLeaguePreviousEvent(leagueId: Int): LiveData<List<Match>> {
        return remoteRepository.getLeaguePreviousEvent(leagueId)
    }

    override fun getLeagueNextEvent(leagueId: Int): LiveData<List<Match>> {
        return remoteRepository.getLeagueNextEvent(leagueId)
    }

    override fun getDetailMatch(matchId: String): LiveData<Match> {
        return remoteRepository.getDetailMatch(matchId)
    }

    override fun getDetailTeam(teamId: String): LiveData<Team> {
       return remoteRepository.getDetailTeam(teamId)
    }

    override fun searchMatch(dataSearch: String): LiveData<List<Match>> {
        return remoteRepository.searchMatch(dataSearch)
    }

    override fun getFavorite(): LiveData<List<Match>> {
        val favoriteMatches = MutableLiveData<List<Match>>()
        myDatabaseOpenHelper.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val tempFavorite = result.parseList(classParser<Favorite>())

            favoriteMatches.value = tempFavorite.map {
                Match(it.dateEvent,it.idAwayTeam,it.idEvent,it.idHomeTeam,it.intAwayScore,it.intHomeScore,it.nameEvent,it.strAwayTeam,it.strHomeTeam)
            }
        }

        return favoriteMatches
    }

    override fun addFavoriteMatch(match: Match): Boolean {
        try {
            myDatabaseOpenHelper.use {
                insert( Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to  match.idEvent,
                    Favorite.EVENT_NAME to match.nameEvent,
                    Favorite.DATE_EVENT to match.dateEvent,
                    Favorite.AWAY_TEAM_ID to match.idAwayTeam,
                    Favorite.AWAY_TEAM_NAME to match.strAwayTeam,
                    Favorite.AWAY_TEAM_SCORE to match.intAwayScore,
                    Favorite.HOME_TEAM_ID to match.idHomeTeam,
                    Favorite.HOME_TEAM_NAME to match.strHomeTeam,
                    Favorite.HOME_TEAM_SCORE to match.intHomeScore)

            }
            return true
        }catch (e: SQLiteConstraintException){
            return false
        }
    }

    override fun removeFavoriteMatch(match: Match): Boolean {
        try {
            myDatabaseOpenHelper.use {
                delete(Favorite.TABLE_FAVORITE,"(EVENT_ID = {id})", "id" to match.idEvent!!)
            }
            return true
        }catch (e: SQLiteConstraintException){
            return false
        }
    }

    override fun favoriteState(match: Match): Boolean {
        var isFavorite = false
        myDatabaseOpenHelper.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to match.idEvent!!)
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }

        return isFavorite
    }

    override fun getLeagueStandings(leagueId: Int): LiveData<UiState<List<Standings>>> {
        return remoteRepository.getLeagueStandings(leagueId)
    }

    override fun getLeagueTeams(leagueId: Int): LiveData<UiState<List<Team>>> {
        return remoteRepository.getLeagueTeams(leagueId)
    }
}