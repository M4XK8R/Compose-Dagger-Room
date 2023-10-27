package com.maxkor.compose_dagger_room

import android.util.Log
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.maxkor.compose_dagger_room.data.db.MainDb
import com.maxkor.compose_dagger_room.data.db.MainEntity
import kotlinx.coroutines.launch
import java.util.Locale

class MainViewModel(mainDb: MainDb) : ViewModel() {
    private val dao = mainDb.mainDao()

    val listOfSomething = dao.getAllStuff()

    fun insert() = viewModelScope.launch {
        val something = createEntity()
        dao.insertSomething(something)
    }

    fun delete(something: MainEntity) = viewModelScope.launch {
        dao.deleteSomething(something)
    }

    private fun createEntity(): MainEntity {
        var title = ""
        var description = ""

        val randomNumber = (3..7).random()
        val randomNumber2 = (6..13).random()

        for (i in 0..randomNumber) {
            val randomChar = ('a'..'z').random()
            title += randomChar
        }

        for (i in 0..randomNumber2) {
            val randomChar = ('a'..'z').random()
            description += randomChar
        }

        val entity = MainEntity(
            title = title.replaceFirstChar {
                it.titlecase(Locale.getDefault())
            },
            description = description.replaceFirstChar {
                it.titlecase()
            }
        )
        return entity
    }

    companion object {
        val factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val db = (checkNotNull(extras[APPLICATION_KEY]) as App).mainDb
                return MainViewModel(db) as T
            }
        }
    }
}