package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDreamWorldSpriteContract(
    @SerialName("front_default")
    val front_default: String? = null,
    @SerialName("front_female")
    val front_female: String? = null
)