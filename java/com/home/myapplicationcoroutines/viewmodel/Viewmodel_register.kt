package com.RadmanHayati.myapplicationcoroutines.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.model.Model_Post
import com.homecat.myapplicationcoroutines.model.Model_Status
import com.homecat.myapplicationcoroutines.model.PostDetials
import kotlinx.coroutines.Job

class Viewmodel_register : ViewModel() {

    lateinit var job : Job
    val mutablelivedata = MutableLiveData<Model_Status>()
    val mutable_checkintent = MutableLiveData<Boolean>()
    var name : String? = null
    var mobile : String? = null
    var email : String? = null
    var pass : String? = null

    fun ClickBtn_login(view : View){
        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().PostRegister(name!!,mobile!!,email!!,pass!!))
        },
            {
                mutablelivedata.value=it
            })
       }
    fun ClickBtn_intent_register(view : View){
        mutable_checkintent.value=true
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}