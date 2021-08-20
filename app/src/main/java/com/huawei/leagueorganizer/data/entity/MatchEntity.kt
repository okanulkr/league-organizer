package com.huawei.leagueorganizer.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val firstTeamId: Int,

    val secondTeamId: Int
)
