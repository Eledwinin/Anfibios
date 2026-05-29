//creado por Edwin Mauricio Morales Rodriguez
package com.example.anfibios.data

import com.example.anfibios.internet.AnfibiosApiService

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class DefaultAppContainer: AppContainer {
    //esta es la url base del servidor de google donde estan las imagenes
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"


    //se construye un objeto retrofit con la url base y un conversor de json
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    //aqui estamos creando el servicio a internet con el lazy
    private val retrofitService: AnfibiosApiService by lazy{
        retrofit.create(AnfibiosApiService::class.java)
    }

    override val anfibiosRepository: AnfibiosRepository by lazy {
        NetworkAnfibiosRepository(retrofitService)
    }

}