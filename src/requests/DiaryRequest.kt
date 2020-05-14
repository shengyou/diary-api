package cn.kraftsman.requests

import cn.kraftsman.utils.CustomDateDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.joda.time.DateTime

data class DiaryRequest(
    val id: Int,
    @JsonDeserialize(using = CustomDateDeserializer::class)
    val time: DateTime,
    val data: String
)
