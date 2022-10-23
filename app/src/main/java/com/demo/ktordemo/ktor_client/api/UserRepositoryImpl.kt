package com.demo.ktordemo.ktor_client.api

class UserRepositoryImpl(private val userApi: UserApi):UserRepository {

    override suspend fun getAgeOfPersonByName(name: String): Result<Any> {
        return try {
            Result.success(userApi.getAgeOfPersonByName(name))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}