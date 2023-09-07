package com.example.api_in_database_combination.data.remote

import com.example.api_in_database_combination.data.model.BreedData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val BASE_URL = "https://catfact.ninja/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("breeds")
    suspend fun getBreedsFromAPI() : BreedData
}

object BreedApi {
    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

//