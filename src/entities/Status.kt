package cn.kraftsman.entities

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Status(
    val status: Int,
    val message: String? = null
)
