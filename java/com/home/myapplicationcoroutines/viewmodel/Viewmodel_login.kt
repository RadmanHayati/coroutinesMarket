package com.RadmanHayati.myapplicationcoroutines.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.model.Model_Post
import com.homecat.myapplicationcoroutines.model.Model_Status
import com.homecat.myapplicationcoroutines.model.PostDetials
import kotlinx.coroutines.Job

class Viewmodel_login(val app : Application) : AndroidViewModel(app) {

    lateinit var job : Job
    val mutablelivedata = MutableLiveData<Model_Status>()
    val mutable_checkintent = MutableLiveData<Boolean>()
    var mobile : String? = null
    var pass : String? = null

    fun ClickBtn_login(view : View){
        if(mobile.isNullOrEmpty()){
            Toast.makeText(app.applicationContext, "شماره موبایل را وارد کنید...", Toast.LENGTH_SHORT).show()
        }else if(pass.isNullOrEmpty()){
            Toast.makeText(app.applicationContext, "رمز عبور را وارد کنید...", Toast.LENGTH_SHORT).show()
        }else
        {
            job= ThridMain.Coroutines_Handel({
                Handel_Requst.Reqqiest(Api.invoke().Postlogin(mobile!!,pass!!))
            },
                {
                    mutablelivedata.value=it
                })
        }
       }
    fun ClickBtn_intent_register(view : View){
        mutable_checkintent.value=true
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}