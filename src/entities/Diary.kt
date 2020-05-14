package cn.kraftsman.entities

import cn.kraftsman.utils.CustomDateDeserializer
import cn.kraftsman.utils.CustomDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.joda.time.DateTime

data class Diary (
    val id:Int,
    @JsonSerialize(using = CustomDateSerializer::class)
    @JsonDeserialize(using = CustomDateDeserializer::class)
    val time: DateTime,
    val data: String
)
