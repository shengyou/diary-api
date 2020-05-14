package cn.kraftsman.responses

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class StatusResponse(
    val status: Int,
    val message: String? = null
)
