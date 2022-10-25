package com.demo.ktordemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.ktordemo.ktor_client.api.UserApi
import com.demo.ktordemo.ktor_client.api.UserRepositoryImpl
import com.demo.ktordemo.ktor_client.httpClientAndroid
import com.demo.ktordemo.ktor_client.model.UserEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Serializing objects
        val data = UserEntity(1,1,"Abhishek")
        val string = Json.encodeToString(data)
        println(string)
        val obj = Json.decodeFromString<UserEntity>(string)
        println(obj)

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