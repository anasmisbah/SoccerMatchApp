package com.example.soccermatch.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.soccermatch.data.local.entity.Favorite
import com.example.soccermatch.data.local.entity.Team
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteSoccer.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.EVENT_NAME to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_TEAM_SCORE to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_TEAM_SCORE to TEXT
        )

        db.createTable(
            Team.TABLE_TEAM_FAVORITE,true,
            Team.TEAM_ID to TEXT + UNIQUE,
            Team.TEAM_BADGE to TEXT,
            Team.TEAM_COUNTRY to TEXT,
            Team.TEAM_DESCRIPTION to TEXT,
            Team.TEAM_FACEBOOK to TEXT,
            Team.TEAM_GENDER to TEXT,
            Team.TEAM_INSTAGRAM to TEXT,
            Team.TEAM_STADIUM_THUMB to TEXT,
            Team.TEAM_NAME to TEXT,
            Team.TEAM_BANNER to TEXT,
            Team.TEAM_FANART1 to TEXT,
            Team.TEAM_FANART2 to TEXT,
            Team.TEAM_FANART3 to TEXT,
            Team.TEAM_FANART4 to TEXT,
            Team.TEAM_JERSEY to TEXT,
            Team.TEAM_LOGO to TEXT,
            Team.TEAM_YOUTUBE to TEXT,
            Team.TEAM_TWITTER to TEXT,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(Team.TABLE_TEAM_FAVORITE, true)
        onCreate(db)
    }
}