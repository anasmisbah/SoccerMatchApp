package com.example.soccermatch.ui.team.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.data.local.entity.Team
import com.example.soccermatch.ui.team.search.SearchTeamActivity
import com.example.soccermatch.utils.EXTRA_LEAGUE
import com.example.soccermatch.utils.EXTRA_TEAM
import com.example.soccermatch.utils.GlideApp
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val teamIntent = intent.getParcelableExtra<Team>(EXTRA_TEAM)
        detail_team_tv_name.text = teamIntent.strTeam
        detail_team_tv_country.text = teamIntent.strCountry
        detail_team_tv_gender.text = teamIntent.strGender
        detail_team_tv_desc.text = teamIntent.strDescriptionEN
        GlideApp.with(this)
            .load(teamIntent.strTeamBadge)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(detail_team_img_badge)
        val teams = ArrayList<String>()
        teamIntent?.strTeamBanner?.let {
            teams.add(it)
        }
        teamIntent?.strTeamBadge?.let {
            teams.add(it)
        }
        teamIntent?.strStadiumThumb?.let {
            teams.add(it)
        }
        teamIntent?.strTeamLogo?.let {
            teams.add(it)
        }
        teamIntent?.strTeamFanart1?.let {
            teams.add(it)
        }
        teamIntent?.strTeamFanart2?.let {
            teams.add(it)
        }
        teamIntent?.strTeamFanart3?.let {
            teams.add(it)
        }
        teamIntent?.strTeamFanart4?.let {
            teams.add(it)
        }
        teamIntent?.strTeamBanner?.let {
            teams.add(it)
        }
        teamIntent?.strTeamJersey?.let {
            teams.add(it)
        }


        val galleryAdapter = GaleryAdapter()
        galleryAdapter.setUrls(teams)
        rv_team_galery.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = galleryAdapter
        }

        detail_team_img_yt.setOnClickListener {
            teamIntent?.strYoutube?.let {
                val webPage: Uri = Uri.parse("https://$it")
                val intentYoutube = Intent(Intent.ACTION_VIEW,webPage)
                startActivity(intentYoutube)
            }
        }

        detail_team_img_fb.setOnClickListener {
            teamIntent?.strFacebook?.let {
                val webPage: Uri = Uri.parse("https://$it")
                val intentFacebook = Intent(Intent.ACTION_VIEW,webPage)
                startActivity(intentFacebook)
            }
        }

        detail_team_img_twt.setOnClickListener {
            teamIntent?.strTwitter?.let {
                val webPage: Uri = Uri.parse("https://$it")
                val intentTwitter = Intent(Intent.ACTION_VIEW,webPage)
                startActivity(intentTwitter)
            }
        }

        detail_team_img_ig.setOnClickListener {
            teamIntent?.strInstagram?.let {
                val webPage: Uri = Uri.parse("https://$it")
                val intentInstagram = Intent(Intent.ACTION_VIEW,webPage)
                startActivity(intentInstagram)
            }
        }




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_search_favorite ->{
                startActivity(Intent(this, SearchTeamActivity::class.java))
            }
            R.id.add_to_favorite ->{
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar_favorite,menu)
        return super.onCreateOptionsMenu(menu)
    }
}
