package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonVarietyContract(
    @SerialName("is_default")
    val is_default : Boolean,
    @SerialName("pokemon")
    val pokemon : PokemonVarietyDetailContract
)