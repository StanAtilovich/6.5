package ru.stan.a65.data.paging.api


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import ru.stan.a65.data.paging.dto.Response

const val BASE_URL = "https://api.potterdb.com/"

interface SearchCharactersPerPageApi {
    @GET("/v1/characters")
    suspend fun getCharacters(): Response
}

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchCharactersApi: SearchCharactersPerPageApi =
        retrofit.create(SearchCharactersPerPageApi::class.java)
}