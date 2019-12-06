package com.example.soccermatch.ui.league.teams


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.soccermatch.R
import com.example.soccermatch.data.UiState
import com.example.soccermatch.ui.league.DetailLeagueViewModel
import com.example.soccermatch.ui.team.TeamAdapter
import com.example.soccermatch.utils.ViewModelFactory
import com.example.soccermatch.utils.invisible
import com.example.soccermatch.utils.visible
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity?.application)).get(
            DetailLeagueViewModel::class.java)
    }
    private var leagueId : Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getMyBundle()
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    private fun getMyBundle() {
        leagueId = arguments?.getInt("id",0)?:0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val teamAdapter = TeamAdapter{
            toast(it.strTeam)
        }

        viewModel.getLeagueTeams(leagueId!!).observe(this, Observer {
            when(it){
                is UiState.Loading->{
                    teams_progressbar.visible()
                }
                is UiState.Success->{
                    teams_progressbar.invisible()
                    teamAdapter.setTeams(it.data)
                    teamAdapter.notifyDataSetChanged()
                }
                is UiState.Error->{
                    toast("Cannot read data properly")
                    Log.e("standingFragment",it.throwable.toString())
                }
            }
        })

        rv_league_team.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = teamAdapter
        }
    }


}
