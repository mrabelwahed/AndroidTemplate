package com.ramadan.theme.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ramadan.theme.R

class ThemeChangeDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val items =  arrayOf("Light","Dark","Default")
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.change_app_theme)
            .setSingleChoiceItems(items,0){
                _,which->
                    dismiss()

            }
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ThemeChangeDialog().show(activity?.supportFragmentManager!! ,"sss")
    }
}