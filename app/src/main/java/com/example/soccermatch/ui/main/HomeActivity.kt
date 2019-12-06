package com.example.soccermatch.ui.main

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccermatch.R
import com.example.soccermatch.ui.league.DetailLeagueActivity
import com.example.soccermatch.ui.main.favorite.FavoriteFragment
import com.example.soccermatch.ui.main.league.LeagueFragment
import com.example.soccermatch.ui.match.search.SearchMatchActivity
import com.example.soccermatch.ui.team.favorite.TeamFavoriteFragment
import com.example.soccermatch.utils.EXTRA_LEAGUE
import com.example.soccermatch.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.leagues ->{
                    loadLeaguesFragment(savedInstanceState)
                }
                R.id.favorites ->{
                    loadFavoritesFragment(savedInstanceState)
                }
                R.id.favorites_team->{
                    loadFavoritesTeamFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.leagues
    }

    private fun loadLeaguesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.bottom_layout, LeagueFragment(), LeagueFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.bottom_layout, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.bottom_layout, TeamFavoriteFragment(), TeamFavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_search ->{
                startActivity(Intent(this, SearchMatchActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
