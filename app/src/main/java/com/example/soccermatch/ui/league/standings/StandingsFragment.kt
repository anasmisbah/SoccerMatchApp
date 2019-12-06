package com.example.soccermatch.ui.league.standings


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
import com.example.soccermatch.ui.league.StandingsAdapter
import com.example.soccermatch.utils.ViewModelFactory
import com.example.soccermatch.utils.invisible
import com.example.soccermatch.utils.visible
import kotlinx.android.synthetic.main.fragment_standings.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class StandingsFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity?.application)).get(
            DetailLeagueViewModel::class.java)
    }
    private var leagueId : Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getMyBundle()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    private fun getMyBundle() {
        leagueId = arguments?.getInt("id",0)?:0
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val standingsAdapter = StandingsAdapter()

        viewModel.getLeagueStandings(leagueId!!).observe(this, Observer {
            when(it){
                is UiState.Loading->{
                    standing_progressbar.visible()
                }
                is UiState.Success->{
                    standing_progressbar.invisible()
                    standingsAdapter.setStandings(it.data)
                    standingsAdapter.notifyDataSetChanged()
                }
                is UiState.Error->{
                    toast("Cannot read data properly")
                    Log.e("standingFragment",it.throwable.toString())
                }
            }
        })

        rv_standings.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = standingsAdapter
        }
    }


}
