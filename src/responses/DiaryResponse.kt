package cn.kraftsman.responses

import cn.kraftsman.utils.CustomDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.joda.time.DateTime

data class DiaryResponse (
    val id:Int,
    @JsonSerialize(using = CustomDateSerializer::class)
    val time: DateTime,
    val data: String
)
