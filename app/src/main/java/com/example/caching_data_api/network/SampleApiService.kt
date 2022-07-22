package com.example.caching_data_api.network

import com.example.caching_data_api.database.User
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://my-json-server.typicode.com/Andhika-Ardyanto/")
    .build()

//Api interface
interface SampleService{
    @GET("test-api/users")
    suspend fun showList():
            List<User>
}

object SampleApi{
    val retrofitService = retrofit.create(SampleService::class.java)
}
