//creado por Edwin Mauricio Morales Rodriguez
package com.example.anfibios.internet

import com.example.anfibios.model.Anfibios
import retrofit2.http.GET

interface AnfibiosApiService {
    @GET("amphibians")
    suspend fun getAnfibios(): List<Anfibios>
}