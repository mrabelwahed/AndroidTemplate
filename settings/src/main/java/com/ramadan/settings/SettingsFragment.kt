package com.ramadan.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var param1: String? = null
    private var param2: String? = null
    private val settingsViewModel:SettingsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changeTheme = view.findViewById<Button>(R.id.changeTheme)
        val versionNameLabel = view.findViewById<TextView>(R.id.versionNameLabel)
        changeTheme.setOnClickListener {
            findNavController().navigate(R.id.actionFromSettingsToTheme)
        }

        settingsViewModel.versionName.observe(
            viewLifecycleOwner,
            {
                    version -> versionNameLabel.text = "Version: $version"
            }
        )



    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}