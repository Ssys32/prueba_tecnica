package com.example.appblog.vo

import com.example.appblog.domain.WebService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS) .build()

    val webservice: WebService by lazy {
        val gson = GsonBuilder().setLenient().create()
        Retrofit.Builder()
            .baseUrl("https://api-prueba-tecnica.000webhostapp.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(WebService::class.java)
    }
}