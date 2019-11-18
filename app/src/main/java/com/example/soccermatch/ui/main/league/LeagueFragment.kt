package com.example.soccermatch.ui.main.league


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.soccermatch.R
import com.example.soccermatch.ui.league.DetailLeagueActivity
import com.example.soccermatch.ui.main.HomeViewModel
import com.example.soccermatch.ui.main.LeagueAdapter
import com.example.soccermatch.utils.EXTRA_LEAGUE
import com.example.soccermatch.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_league.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity?.application)).get(
            HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val leagueAdapter = LeagueAdapter{league,imageView ->
            val detailLeagueIntent = Intent(this.context, DetailLeagueActivity::class.java)
            detailLeagueIntent.putExtra(EXTRA_LEAGUE,league)
            val options = ActivityOptions.makeSceneTransitionAnimation(this.activity,imageView,"sharedName")
            startActivity(detailLeagueIntent,options.toBundle())
        }

        viewModel.getLeagues().observe(this, Observer {
            leagueAdapter.setLeagues(it)
            leagueAdapter.notifyDataSetChanged()
        })

        rv_leagues.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            adapter = leagueAdapter
        }
    }


}
