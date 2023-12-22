package com.example.appblog.domain

import com.example.appblog.data.Entrada
import com.example.appblog.data.model.EntradaEntity
import com.example.appblog.vo.Resource

interface Repo {
    suspend fun getAllEntradasList(entrada: String): Resource<List<Entrada>>

    suspend fun getEntradasList(): Resource<List<Entrada>>

    suspend fun postEntrada(
        titulo: String,
        autor: String,
        fecha: String,
        contenido: String
    ): Resource<Entrada>

    /*-----Metodos para guardar en Room--------*/

    suspend fun getentradasLocal(): Resource<List<EntradaEntity>>

    suspend fun insertEntradaLocal(entradaLocal: EntradaEntity)
}