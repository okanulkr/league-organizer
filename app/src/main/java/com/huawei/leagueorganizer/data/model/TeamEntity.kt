package com.huawei.leagueorganizer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,

    val logo: String
)
