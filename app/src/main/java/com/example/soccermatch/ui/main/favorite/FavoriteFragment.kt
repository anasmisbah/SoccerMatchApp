package com.example.soccermatch.ui.main.favorite

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.soccermatch.R
import com.example.soccermatch.ui.league.LeagueMatchAdapter
import com.example.soccermatch.ui.main.HomeViewModel
import com.example.soccermatch.ui.match.DetailMatchActivity
import com.example.soccermatch.utils.EXTRA_MATCH
import com.example.soccermatch.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity?.application)).get(
            HomeViewModel::class.java)
    }
    lateinit var matchAdapter: LeagueMatchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

         matchAdapter = LeagueMatchAdapter{
            val detailMatchIntent = Intent(context, DetailMatchActivity::class.java)
            detailMatchIntent.putExtra(EXTRA_MATCH,it)
            startActivity(detailMatchIntent)
        }



        rv_favorite_match.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMatches().observe(this, Observer {
            matchAdapter.setMatchs(it)
            matchAdapter.notifyDataSetChanged()
        })
    }
}
