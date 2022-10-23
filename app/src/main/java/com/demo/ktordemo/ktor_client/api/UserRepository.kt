package com.demo.ktordemo.ktor_client.api

interface UserRepository {
    suspend fun getAgeOfPersonByName( name: String): Result<Any>
}