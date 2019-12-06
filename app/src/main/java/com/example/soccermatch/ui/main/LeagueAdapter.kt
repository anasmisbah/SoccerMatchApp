package com.example.soccermatch.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.League
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter  internal constructor(
    private val listener: (League,ImageView) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>(){

    private var leagues = emptyList<League>()

    internal fun setLeagues(league: ArrayList<League>){
        this.leagues = league
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_league,parent,false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(leagues[position],listener)
    }


    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(league: League,listener: (League,ImageView) -> Unit) = with(itemView){
            tv_league_name.text = league.name

            league.badge?.let {
                img_badge_league.setImageResource(it)
            }


            setOnClickListener { listener(league,img_badge_league) }
        }
    }
}