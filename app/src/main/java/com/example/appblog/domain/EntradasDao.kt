package com.example.appblog.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appblog.data.model.EntradaEntity

@Dao
interface EntradasDao {

    @Query("SELECT * FROM entrada_table")
    suspend fun getAllSaveEntradas(): List<EntradaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEntradaLocal(entrada: EntradaEntity)
}