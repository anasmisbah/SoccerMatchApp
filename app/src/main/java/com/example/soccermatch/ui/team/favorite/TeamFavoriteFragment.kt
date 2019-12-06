package com.example.soccermatch.ui.team.favorite


import android.app.ActivityOptions
import android.content.Intent
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
import com.example.soccermatch.ui.team.TeamAdapter
import com.example.soccermatch.ui.team.TeamViewModel
import com.example.soccermatch.ui.team.detail.DetailTeamActivity
import com.example.soccermatch.utils.EXTRA_TEAM
import com.example.soccermatch.utils.ViewModelFactory
import com.example.soccermatch.utils.invisible
import com.example.soccermatch.utils.visible
import kotlinx.android.synthetic.main.fragment_team_favorite.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class TeamFavoriteFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity?.application)).get(
            TeamViewModel::class.java)
    }
    lateinit var teamAdapter: TeamAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamAdapter = TeamAdapter{team,imageView->
            val detailLeagueIntent = Intent(this.context, DetailTeamActivity::class.java)
            detailLeagueIntent.putExtra(EXTRA_TEAM,team)
            val options = ActivityOptions.makeSceneTransitionAnimation(this.activity,imageView,"teambadge")
            startActivity(detailLeagueIntent,options.toBundle())
        }



        rv_favorite_team.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = teamAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTeam().observe(this, Observer {
            when(it){
                is UiState.Loading->{
                }
                is UiState.Success->{
                    Log.d("teamFragment",it.data.size.toString())
                    teamAdapter.setTeams(it.data)
                    teamAdapter.notifyDataSetChanged()
                }
                is UiState.Error->{
                    toast("Cannot read data properly")
                    Log.e("teamFragment",it.throwable.toString())
                }
            }
        })
    }


}
