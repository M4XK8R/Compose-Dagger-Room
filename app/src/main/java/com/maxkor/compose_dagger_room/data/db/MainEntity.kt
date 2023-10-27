package com.maxkor.compose_dagger_room.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MAIN_TABLE_NAME)
data class MainEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val description: String
)