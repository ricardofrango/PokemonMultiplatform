package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonGenerationContract(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)