package com.maxkor.compose_dagger_room.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomething(mainEntity: MainEntity)

    @Delete
    suspend fun deleteSomething(mainEntity: MainEntity)

    @Query("SELECT * FROM $MAIN_TABLE_NAME where id = (:id)")
    suspend fun getStuff(id: Int): MainEntity

    @Query("SELECT * FROM $MAIN_TABLE_NAME")
    fun getAllStuff(): Flow<List<MainEntity>>
}