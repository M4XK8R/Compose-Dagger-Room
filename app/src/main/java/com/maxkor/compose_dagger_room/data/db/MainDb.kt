package com.maxkor.compose_dagger_room.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "main_db"

@Database(entities = [MainEntity::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object {
        @Volatile
        private var db: MainDb? = null

        fun getInstance(context: Context): MainDb {
            return db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MainDb::class.java,
                    DB_NAME
                ).build()
                db = instance
                instance
            }
        }
    }
}