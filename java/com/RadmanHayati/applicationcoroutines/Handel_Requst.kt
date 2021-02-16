package com.RadmanHayati.applicationcoroutines

import retrofit2.Response

object Handel_Requst : Respositry() {
    suspend fun <T:Any> Reqqiest(Api:Response<T>)=CustomRequst {
        Api
    }
}