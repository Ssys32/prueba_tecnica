package com.example.appblog.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.appblog.data.model.EntradaEntity
import com.example.appblog.domain.Repo
import com.example.appblog.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {

    val fetchEntradasList =
        liveData(Dispatchers.IO) {
                emit(Resource.Loading())
                try {
                    emit(repo.getEntradasList())
                } catch (e: Exception) {
                    emit(Resource.Failure(e))
                    Log.d("Mi error", e.toString())
                }
        }


    fun insertarEntrada(titulo: String, autor: String, fecha: String, contenido: String) {
        viewModelScope.launch {
            repo.postEntrada(titulo, autor, fecha, contenido)
        }
    }

    /*-----------------ROOM--------------------*/

    fun guardarEntrada(entrada: EntradaEntity) {
        viewModelScope.launch {
            repo.insertEntradaLocal(entrada)
        }
    }

    fun getEntradasLocal() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getentradasLocal())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}