package com.ramadan.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor()  : ViewModel(){
    var _versionName= MutableLiveData<String>()
    val versionName : LiveData<String>
    get() = _versionName

    fun setVersionName(version:String){
        _versionName.value = version
    }
}