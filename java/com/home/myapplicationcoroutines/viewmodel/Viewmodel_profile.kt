package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.model.Model_Listorder
import com.homecat.myapplicationcoroutines.model.Model_Post
import com.homecat.myapplicationcoroutines.model.Model_Userinfo
import com.homecat.myapplicationcoroutines.model.PostDetials
import kotlinx.coroutines.Job

class Viewmodel_profile : ViewModel() {

    lateinit var job : Job
    val Mutable_userinfo = MutableLiveData<List<Model_Userinfo>>()
    val Mutable_listorder = MutableLiveData<List<Model_Listorder>>()

    fun Listpost(user:String){
        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_userinfo(user))
        },
         {
             Mutable_userinfo.value=it
        })

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_Listorder(user))
        },
            {
                Mutable_listorder.value=it
            })


    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}