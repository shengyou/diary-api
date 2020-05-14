package cn.kraftsman.responses

import org.joda.time.DateTime

data class DiaryResponse (
    val id:Int,
    val time: DateTime,
    val data: String
)
