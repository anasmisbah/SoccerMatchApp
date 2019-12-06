package com.example.soccermatch.ui.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.Match
import kotlinx.android.synthetic.main.item_match.view.*

class LeagueMatchAdapter internal constructor(
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<LeagueMatchAdapter.LeagueMatchViewHolder>(){

    private var matches = emptyList<Match>()

    internal fun setMatchs(matches: List<Match>){
        this.matches = matches
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueMatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match,parent,false)
        return LeagueMatchViewHolder(view)
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: LeagueMatchViewHolder, position: Int) {
       holder.bindItem(matches[position],listener)
    }


    inner class LeagueMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(match: Match,listener: (Match) -> Unit) = with(itemView){
            tv_away_team.text = match.strAwayTeam
            tv_home_team.text = match.strHomeTeam
            tv_title_match.text = match.nameEvent
            tv_skor_home.text = match.intHomeScore?:"-"
            tv_skor_away.text = match.intAwayScore?:"-"
            tv_date_match.text = match.dateEvent
            setOnClickListener {
                listener(match)
            }
        }
    }

}