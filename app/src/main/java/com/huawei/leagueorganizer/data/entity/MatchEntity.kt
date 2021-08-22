package com.huawei.leagueorganizer.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["firstTeamId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["secondTeamId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MatchEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(index = true)
    val firstTeamId: Int,

    @ColumnInfo(index = true)
    val secondTeamId: Int,

    val week: Int
)
