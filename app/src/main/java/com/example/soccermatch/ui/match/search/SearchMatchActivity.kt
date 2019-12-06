package com.example.soccermatch.ui.match.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccermatch.R
import com.example.soccermatch.ui.league.LeagueMatchAdapter
import com.example.soccermatch.ui.match.DetailMatchActivity
import com.example.soccermatch.ui.match.MatchViewModel
import com.example.soccermatch.utils.EXTRA_MATCH
import com.example.soccermatch.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search_match.*

class SearchMatchActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.application)).get(
            MatchViewModel::class.java)
    }
    lateinit var matchAdapter : LeagueMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchAdapter = LeagueMatchAdapter{
            val detailMatchIntent = Intent(this, DetailMatchActivity::class.java)
            detailMatchIntent.putExtra(EXTRA_MATCH,it)
            startActivity(detailMatchIntent)
        }

        rv_search_match.apply {
            layoutManager = LinearLayoutManager(this@SearchMatchActivity)
            setHasFixedSize(true)
            adapter = matchAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_nav,menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.expandActionView()
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "search match.."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchMatch(query?:"").observe(this@SearchMatchActivity, Observer {
                    matchAdapter.setMatchs(it)
                    matchAdapter.notifyDataSetChanged()
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
