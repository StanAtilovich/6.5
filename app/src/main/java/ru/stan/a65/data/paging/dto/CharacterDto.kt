package ru.stan.a65.data.paging.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "data")
    val data: List<DataJson>
)

@JsonClass(generateAdapter = true)
data class DataJson(
    val id: String,
    @Json(name = "attributes")
    val attributes: Attributes
)

@JsonClass(generateAdapter = true)
data class Attributes(
    @Json(name = "name")
    val name: String?,
    @Json(name = "blood_status")
    val bloodStatus: String?,
    @Json(name = "house")
    val hogwartsHouse: String?,
    @Json(name = "patronus")
    val patronus: String?,
    @Json(name = "image")
    val imageUrl: String?
)