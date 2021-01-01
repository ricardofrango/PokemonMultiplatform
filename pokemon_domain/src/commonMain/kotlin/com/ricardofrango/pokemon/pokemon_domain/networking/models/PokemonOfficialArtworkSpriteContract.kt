package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class PokemonOfficialArtworkSpriteContract(
    @SerialName("front_default")
    val front_default: String? = null
)