//creado por Edwin Mauricio Morales Rodriguez
package com.example.anfibios.data

import com.example.anfibios.internet.AnfibiosApiService
import com.example.anfibios.model.Anfibios

class NetworkAnfibiosRepository (
    private val anfibiosApiService: AnfibiosApiService //se le inyecta el servicio a inter
    ) : AnfibiosRepository {
        override suspend fun getAnfibios(): List<Anfibios> = anfibiosApiService.getAnfibios()

}

