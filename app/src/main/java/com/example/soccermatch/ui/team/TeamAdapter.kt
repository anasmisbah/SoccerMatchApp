package com.example.soccermatch.ui.team

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.Team
import com.example.soccermatch.utils.GlideApp
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter internal constructor(
    private val listener: (Team, ImageView) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(){

    private var teams = emptyList<Team>()

    internal fun setTeams(teams: List<Team>){
        this.teams = teams
        notifyDataSetChanged()
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(team: Team,listener: (Team,ImageView) -> Unit) = with(itemView){
            Log.d("TeamViewHolder",team.strTeam)
            GlideApp.with(this)
                .load(team.strTeamBadge)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(item_team_img_badge)
            item_team_tv_name.text = team.strTeam

            setOnClickListener { listener(team,item_team_img_badge) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_team,parent,false)
        return TeamViewHolder(view)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position],listener)
    }
}