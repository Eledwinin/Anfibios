package com.example.anfibios

import android.app.Application
import com.example.anfibios.data.AppContainer
import com.example.anfibios.data.DefaultAppContainer

class AnfibiosApplication : Application() {
    // Aquí vive la caja de herramientas que el ViewModel está buscando desesperadamente
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Inicializamos el contenedor global en cuanto se enciende el cel
        container = DefaultAppContainer()
    }
}