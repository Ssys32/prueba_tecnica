package com.example.appblog.domain

import com.example.appblog.data.Entrada
import retrofit2.http.*

interface WebService {

    @GET("post.php")
    suspend fun getEntradaAll(@Query("autor") entrada:String): List<Entrada>

    @GET("post.php")
    suspend fun getEntrada(): List<Entrada>

    @FormUrlEncoded
    @POST("post.php/")
    suspend fun postEntrada(
        @Field("titulo") tit: String?,
        @Field("autor") aut: String?,
        @Field("fecha") fe: String?,
        @Field("contenido") cont: String?
    ): Entrada

}