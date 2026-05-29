//creado por Edwin Mauricio Morales Rodriguez
package com.example.anfibios.data

import com.example.anfibios.model.Anfibios

interface AnfibiosRepository {
    suspend fun getAnfibios(): List<Anfibios>
}