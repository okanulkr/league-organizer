package com.huawei.leagueorganizer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.huawei.leagueorganizer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // onBackPressed disabled
    override fun onBackPressed() {
    }
}