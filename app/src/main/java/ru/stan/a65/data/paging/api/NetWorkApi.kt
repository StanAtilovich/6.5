package ru.stan.a65.data.paging.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.stan.a65.data.paging.dto.Response

const val BASE_URL = "https://api.potterdb.com/"

interface SearchCharactersPerPageApi {
    @GET("/v1/characters")
    suspend fun getCharacters(@Query("page[number]") page: Int = 1): Response
}

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchCharactersApi: SearchCharactersPerPageApi =
        retrofit.create(SearchCharactersPerPageApi::class.java)
}