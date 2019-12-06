package com.example.soccermatch.ui.team.search

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccermatch.R
import com.example.soccermatch.data.UiState
import com.example.soccermatch.ui.team.TeamAdapter
import com.example.soccermatch.ui.team.TeamViewModel
import com.example.soccermatch.ui.team.detail.DetailTeamActivity
import com.example.soccermatch.utils.EXTRA_TEAM
import com.example.soccermatch.utils.ViewModelFactory
import com.example.soccermatch.utils.invisible
import com.example.soccermatch.utils.visible
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.toast

class SearchTeamActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.application)).get(
            TeamViewModel::class.java)
    }
    lateinit var teamAdapter: TeamAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        supportActionBar?.title = "Search Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        teamAdapter = TeamAdapter{team,imageView->
            val detailLeagueIntent = Intent(this, DetailTeamActivity::class.java)
            detailLeagueIntent.putExtra(EXTRA_TEAM,team)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,imageView,"teambadge")
            startActivity(detailLeagueIntent,options.toBundle())
        }

        rv_search_team.apply {
            layoutManager = LinearLayoutManager(this@SearchTeamActivity)
            setHasFixedSize(true)
            adapter = teamAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_nav,menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.expandActionView()
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "search team..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchTeam(query?:"").observe(this@SearchTeamActivity, Observer {
                    when(it){
                        is UiState.Loading->{
                            search_team_progressbar.visible()
                            tv_not_found_team.invisible()
                        }
                        is UiState.Success->{
                            search_team_progressbar.invisible()
                            if (it.data.isEmpty()) tv_not_found_team.visible()
                            teamAdapter.setTeams(it.data)
                            teamAdapter.notifyDataSetChanged()
                        }
                        is UiState.Error->{
                            search_team_progressbar.invisible()
                            toast("Cannot read data properly")
                            Log.e("standingFragment",it.throwable.toString())
                        }
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
