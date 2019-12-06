package com.example.soccermatch.ui.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.Standings
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingsAdapter : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>(){

    private var standings = emptyList<Standings>()

    internal fun setStandings(standings: List<Standings>){
        this.standings = standings
        notifyDataSetChanged()
    }

    inner class StandingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(standing: Standings) = with(itemView){
            tv_item_standing_name.text = standing.name
            tv_item_standing_goal.text = "${standing.goal}"
            tv_item_standing_draw.text = "${standing.draw}"
            tv_item_standing_loss.text = "${standing.loss}"
            tv_item_standing_win.text = "${standing.win}"
            tv_item_standing_played.text = "${standing.played}"
            tv_item_standing_total.text = "${standing.total}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_standing,parent,false)
        return StandingsViewHolder(view)
    }

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        holder.bindItem(standings[position])
    }

}