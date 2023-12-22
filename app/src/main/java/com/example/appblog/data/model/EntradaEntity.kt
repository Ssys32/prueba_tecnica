package com.example.appblog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entrada_table")
data class EntradaEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "entrada_titulo") val titulo: String,
    @ColumnInfo(name = "entrada_autor") val autor: String,
    @ColumnInfo(name = "entrada_fecha") val fecha: String,
    @ColumnInfo(name = "entrada_contenido") val contenido: String

)
