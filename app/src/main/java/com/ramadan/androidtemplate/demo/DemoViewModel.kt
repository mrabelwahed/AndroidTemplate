package com.ramadan.androidtemplate.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadan.test_utils.ContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel (private val demoUsecase : DemoUsecase ,
                      private val contextProvider : ContextProvider)
  : ViewModel() {

    private val handle = CoroutineExceptionHandler { coroutineContext, throwable ->
        stateLiveData.value =  DemoViewState.Error(throwable)
    }
     private val stateLiveData = MutableLiveData<DemoViewState>()
    fun getStateLiveData()  :LiveData<DemoViewState> = stateLiveData

     fun getData(){
         stateLiveData.value = DemoViewState.Loading
         viewModelScope.launch (){
           val data  = withContext(contextProvider.IO()){
               // long operation
               demoUsecase.getData() // return string for example
           }
             stateLiveData.value = DemoViewState.Success(data)
         }
     }

}