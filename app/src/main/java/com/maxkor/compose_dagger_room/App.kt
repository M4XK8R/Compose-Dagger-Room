package com.maxkor.compose_dagger_room

import android.app.Application
import com.maxkor.compose_dagger_room.data.db.MainDb

class App : Application() {

    val mainDb by lazy { MainDb.getInstance(this) }
}