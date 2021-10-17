package com.ramadan.theme.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.ramadan.theme.R

class ThemeChangeDialog : DialogFragment(R.layout.dialog_theme) {


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val themeChangeCroup = view.findViewById<RadioGroup>(R.id.themeChangeGroup)
        themeChangeCroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.systemDefault -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            dismiss()
        }
    }

}