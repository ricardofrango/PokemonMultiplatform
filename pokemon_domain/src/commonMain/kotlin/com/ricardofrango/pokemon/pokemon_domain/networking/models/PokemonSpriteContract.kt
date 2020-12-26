package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpriteContract(
    @SerialName("front_default")
    val front_default : String
)