//creado por Edwin Mauricio Morales Rodriguez
package com.example.anfibios.model
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Anfibios(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src") val imgSrc: String
)