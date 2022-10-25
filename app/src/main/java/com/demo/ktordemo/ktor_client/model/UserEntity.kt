package com.demo.ktordemo.ktor_client.model

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

@Serializable(with = UserEntitySerializer::class)
@OptIn(ExperimentalSerializationApi::class)
@Serializer(UserEntity::class)
data class UserEntity(
                        @SerialName("age")
                        val age: Int,
                        @SerialName("count")
                        val count: Int,
                        @SerialName("name")
                        val name: String,)

class UserEntitySerializer: KSerializer<UserEntity>{
    override fun deserialize(decoder: Decoder): UserEntity = decoder.decodeStructure(descriptor) {
        var age = -1
        var count = -1
        var name = ""


        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> age = decodeIntElement(descriptor, 0)
                1 -> count = decodeIntElement(descriptor, 1)
                2 -> name = decodeStringElement(descriptor, 2)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        UserEntity(age, count, name)
    }

    override val descriptor: SerialDescriptor
        get() =  buildClassSerialDescriptor("UserEntity") {
            element<Int>("age")
            element<Int>("count")
            element<String>("name")
        }

    override fun serialize(encoder: Encoder, value: UserEntity) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.age)
            encodeIntElement(descriptor, 1, value.count)
            encodeStringElement(descriptor, 2, value.name)
        }
    }

}
