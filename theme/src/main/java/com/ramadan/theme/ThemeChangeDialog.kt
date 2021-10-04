package com.ramadan.theme

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ThemeChangeDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val data = arrayOf("Light,Dark, Default")
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.title_choose_theme)
            .setSingleChoiceItems(data , 0){_,which ->
              dismiss()
            }.create()
    }
}