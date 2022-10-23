package com.demo.ktordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.demo.ktordemo.ktor_client.api.UserApi
import com.demo.ktordemo.ktor_client.api.UserRepositoryImpl
import com.demo.ktordemo.ktor_client.httpClientAndroid
import io.ktor.client.request.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       GlobalScope.async {
           getUserByName("meelad")
       }


    }

    suspend fun getUserByName(name:String){
        val userApi=UserApi(httpClientAndroid)
        val response= UserRepositoryImpl(userApi).getAgeOfPersonByName(name)
        if(response.isSuccess)
            Log.e("Response",response.getOrNull().toString())
        else
            Log.e("response error",response.exceptionOrNull().toString())
    }

}