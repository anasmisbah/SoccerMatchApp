package com.example.soccermatch.ui.match

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import com.example.soccermatch.R
import com.example.soccermatch.data.local.entity.Match
import com.example.soccermatch.data.local.entity.Team
import com.example.soccermatch.ui.match.search.SearchMatchActivity
import com.example.soccermatch.utils.EXTRA_MATCH
import com.example.soccermatch.utils.GlideApp
import com.example.soccermatch.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.application)).get(MatchViewModel::class.java)
    }

    lateinit var matchIntent : Match
    private var isFavorite = false
    private var menuItem: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchIntent = intent.getParcelableExtra(EXTRA_MATCH)!!
        supportActionBar?.title = matchIntent.nameEvent
        tv_detail_match_title.text = matchIntent.nameEvent
        tv_detail_match_date.text = matchIntent.dateEvent
        tv_detail_home_team.text = matchIntent.strHomeTeam
        tv_detail_home_skor.text = matchIntent.intHomeScore ?: "-"
        tv_detail_away_team.text = matchIntent.strAwayTeam
        tv_detail_away_skor.text = matchIntent.intAwayScore ?: "-"

        viewModel.getDetailMatch(matchIntent.idEvent?:"").observe(this, Observer {
            setDataView(it)
        })

        viewModel.getDetailAwayTeam(matchIntent.idAwayTeam?:"").observe(this, Observer {
            setImage(it,img_detail_away_badge)
        })

        viewModel.getDetailHomeTeam(matchIntent.idHomeTeam?:"").observe(this, Observer {
            setImage(it,img_detail_home_badge)
        })

        if(viewModel.favoriteState(matchIntent)) isFavorite =true
    }

    private fun setImage(team: Team, imgView: ImageView) {
        GlideApp.with(this)
            .load(team.strTeamBadge)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(imgView)
    }

    private fun setDataView(match: Match) {
        tv_detail_home_goal.text = match.strHomeGoalDetails ?: "-"
        tv_detail_home_redcard.text = match.strHomeRedCards ?: "-"
        tv_detail_home_yllowcard.text = match.strHomeYellowCards ?: "-"
        tv_detail_home_goalkeep.text = match.strHomeLineupGoalkeeper ?: "-"
        tv_detail_home_forward.text = match.strHomeLineupForward ?: "-"
        tv_detail_home_formation.text = "${match.strHomeFormation ?: "-"}"

        tv_detail_away_goal.text = match.strAwayGoalDetails ?: "-"
        tv_detail_away_redcard.text = match.strAwayRedCards ?: "-"
        tv_detail_away_yllowcard.text = match.strAwayYellowCards ?: "-"
        tv_detail_away_goalkeep.text = match.strAwayLineupGoalkeeper ?: "-"
        tv_detail_away_forward.text = match.strAwayLineupForward ?: "-"
        tv_detail_away_formation.text = "${match.strAwayFormation ?: "-"}"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar_favorite,menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_search_favorite ->{
                startActivity(Intent(this, SearchMatchActivity::class.java))
            }
            R.id.add_to_favorite ->{
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite(){
        if (viewModel.addFavorite(matchIntent)) toast("berhasil menambahkan ke favorite") else toast("gagal menambah ke favorite")
    }

    private fun removeFromFavorite(){
        if (viewModel.removeFavorite(matchIntent)) toast("berhasil menghapus dari favorite") else toast("gagal menghapus dari favorite")
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
