package com.huawei.leagueorganizer.utils

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.huawei.leagueorganizer.R

object Preferences {
    fun getTeamCount(context: Context) : Int {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.team_count), Constants.TEAM_COUNT)
    }

    fun setTeamCount(context: Context, count: Int) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putInt(context.getString(R.string.team_count), count)
            apply()
        }
    }
}