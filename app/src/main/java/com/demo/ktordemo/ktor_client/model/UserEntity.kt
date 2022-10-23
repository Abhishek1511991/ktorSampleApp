package com.demo.ktordemo.ktor_client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
                        @SerialName("age")
                        val age: Int,
                        @SerialName("count")
                        val count: Int,
                        @SerialName("name")
                        val name: String,)
