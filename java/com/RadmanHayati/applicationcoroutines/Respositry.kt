package com.RadmanHayati.applicationcoroutines

import android.content.Context
import retrofit2.Response

abstract class Respositry {
    suspend fun <T:Any> CustomRequst(Api:()->Response<T>) : T{
        val response  = Api.invoke()
        if(response.isSuccessful)
            return response.body()!!
            throw Exception(response.message())
    }

   companion object{
       fun shardwrite(content : Context,userid:String){
           val shard = content.getSharedPreferences("token",0)
           val editor = shard.edit()
           editor.putString("userid",userid)
           editor.apply()
       }

       fun shardread(content : Context) : String{
           val shard = content.getSharedPreferences("token",0)
           var userid = shard.getString("userid",null)
           userid?.let {
               return userid!!
           }
           return "notfind"
       }
   }


}