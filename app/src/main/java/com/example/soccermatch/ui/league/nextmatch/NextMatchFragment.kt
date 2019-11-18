package com.example.soccermatch.ui.league.nextmatch


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.soccermatch.R
import com.example.soccermatch.ui.league.DetailLeagueViewModel
import com.example.soccermatch.ui.league.LeagueMatchAdapter
import com.example.soccermatch.ui.match.DetailMatchActivity
import com.example.soccermatch.utils.EXTRA_MATCH
import com.example.soccermatch.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.fragment_previous_match.*

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    private fun getMyBundle() {
        leagueId = arguments?.getInt("id",0)?:0
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val matchAdapter = LeagueMatchAdapter{
            val detailMatchIntent = Intent(context, DetailMatchActivity::class.java)
            detailMatchIntent.putExtra(EXTRA_MATCH,it)
            startActivity(detailMatchIntent)
        }

        viewModel.getNextMatch(leagueId!!).observe(this, Observer {
            matchAdapter.setMatchs(it)
            matchAdapter.notifyDataSetChanged()
        })

        rv_next_match.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }
    }


}
