package com.example.soccermatch.ui.league

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.League
import com.example.soccermatch.ui.league.match.MatchFragment
import com.example.soccermatch.ui.league.nextmatch.NextMatchFragment
import com.example.soccermatch.ui.league.previousmatch.PreviousMatchFragment
import com.example.soccermatch.ui.league.standings.StandingsFragment
import com.example.soccermatch.ui.league.teams.TeamFragment
import com.example.soccermatch.ui.match.search.SearchMatchActivity
import com.example.soccermatch.utils.*
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.application)).get(DetailLeagueViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        setSupportActionBar(league_toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var ct : CollapsingToolbarLayout = findViewById<View>(R.id.detailCollapsingToolbar) as CollapsingToolbarLayout
        ct.setExpandedTitleColor(Color.TRANSPARENT)
        ct.setCollapsedTitleTextColor(Color.WHITE)


        val leagueIntent = intent.getParcelableExtra<League>(EXTRA_LEAGUE)
        detail_tv_league_name.text = leagueIntent.name
        ct.title = leagueIntent.name
        leagueIntent.badge?.let {
            detail_league_badge.setImageResource(it)
        }

        viewModel.getDetailLeague(leagueIntent.id).observe(this, Observer {
            setViewDetail(it)
        })

        val fragmentAdapter = DetailViewPagerAdapter(supportFragmentManager,leagueIntent.id)
        fragmentAdapter.addFragment(MatchFragment())
        fragmentAdapter.addFragment(StandingsFragment())
        fragmentAdapter.addFragment(TeamFragment())
        fragmentAdapter.addTitle("Match")
        fragmentAdapter.addTitle("Standings")
        fragmentAdapter.addTitle("Teams")
        detail_league_viewpager.adapter = fragmentAdapter
        detail_league_tablayout.setupWithViewPager(detail_league_viewpager)


    }



    private fun setViewDetail(league: League) {
        detail_league_tv_description.text = league.description
        detail_league_tv_since.text = "Since ${league.since}"

        GlideApp.with(this)
            .load(league.logo)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(detail_league_img_logo)
        GlideApp.with(this)
            .load(league.banner)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(detail_league_img_banner)
        GlideApp.with(this)
            .load(league.trophy)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(detail_league_img_trophy)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_search ->{
                startActivity(Intent(this,SearchMatchActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
