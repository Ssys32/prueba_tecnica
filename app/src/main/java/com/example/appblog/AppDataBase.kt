package com.example.appblog

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appblog.data.model.EntradaEntity
import com.example.appblog.domain.EntradasDao

@Database(entities = arrayOf(EntradaEntity::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun entradaDao(): EntradasDao

    companion object {

        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "entrada_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}