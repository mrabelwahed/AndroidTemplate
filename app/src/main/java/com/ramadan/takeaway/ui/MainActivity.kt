package com.ramadan.takeaway.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ramadan.settings.SettingsViewModel
import com.ramadan.takeaway.BuildConfig
import com.ramadan.takeaway.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingsViewModel.setVersionName(BuildConfig.VERSION_NAME)
    }


}
