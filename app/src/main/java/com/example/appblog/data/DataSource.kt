package com.example.appblog.data

import com.example.appblog.AppDataBase
import com.example.appblog.data.model.EntradaEntity
import com.example.appblog.vo.Resource
import com.example.appblog.vo.RetrofitClient

class DataSource(private val appDataBase: AppDataBase) {

    suspend fun getEntradaAll(entradaAutor: String): Resource<List<Entrada>> {
        return Resource.Success(RetrofitClient.webservice.getEntradaAll(entradaAutor))
    }

    suspend fun getEntrada(): Resource<List<Entrada>> {
        return Resource.Success(RetrofitClient.webservice.getEntrada())
    }

    suspend fun postEntrada(
        titulo: String,
        autor: String,
        fecha: String,
        contenido: String
    ): Resource<Entrada> {
        return Resource.Success(
            RetrofitClient.webservice.postEntrada(
                titulo,
                autor,
                fecha,
                contenido
            )
        )
    }

    /*-----Metodos para guardar en Room--------*/
    suspend fun insertEntradaRoom(entrada: EntradaEntity) {
        appDataBase.entradaDao().saveEntradaLocal(entrada)
    }

    suspend fun getEntradasRoom(): Resource<List<EntradaEntity>> {
        return Resource.Success(appDataBase.entradaDao().getAllSaveEntradas())
    }

    suspend fun getEntradasAutorRoom(autor: String): Resource<List<EntradaEntity>> {
        return Resource.Success(appDataBase.entradaDao().getAllSaveEntradas())
    }
}