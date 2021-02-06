package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeContract(
    @SerialName("slot")
    val slot : Int,
    @SerialName("type")
    val type : TypeContract
)