package com.example.appblog.domain

import com.example.appblog.data.DataSource
import com.example.appblog.data.Entrada
import com.example.appblog.data.model.EntradaEntity
import com.example.appblog.vo.Resource

class RepoImplements(private val dataSource: DataSource) : Repo {

    override suspend fun getAllEntradasList(entrada: String): Resource<List<Entrada>> {
        return dataSource.getEntradaAll(entrada)
    }

    override suspend fun getEntradasList(): Resource<List<Entrada>> {
        return  dataSource.getEntrada()
    }

    override suspend fun postEntrada(titulo: String,autor: String,fecha: String,contenido: String): Resource<Entrada> {
        return  dataSource.postEntrada(titulo, autor, fecha, contenido)
    }



    /*-----Metodos para guardar en Room--------*/

    override suspend fun getentradasLocal(): Resource<List<EntradaEntity>> {
        return  dataSource.getEntradasRoom()
    }

    override suspend fun insertEntradaLocal(entradaLocal: EntradaEntity) {
        dataSource.insertEntradaRoom(entradaLocal)
    }


}