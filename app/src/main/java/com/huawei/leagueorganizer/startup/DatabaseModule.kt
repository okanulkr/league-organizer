package com.huawei.leagueorganizer.startup

import android.content.Context
import androidx.room.Room
import com.huawei.leagueorganizer.data.datasource.local.LeagueOrganizerDatabase
import com.huawei.leagueorganizer.data.dao.MatchDAO
import com.huawei.leagueorganizer.data.dao.TeamDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideTeamDAO(database: LeagueOrganizerDatabase): TeamDAO = database.teamDAO

    @Provides
    fun provideMatchDAO(database: LeagueOrganizerDatabase): MatchDAO = database.matchDAO

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): LeagueOrganizerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LeagueOrganizerDatabase::class.java,
            "league_db"
        ).build()
    }
}