package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonContract(
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("sprites")
    val sprites: PokemonSpriteContract,
    @SerialName("forms")
    val forms: List<PokemonFormContract>
)
