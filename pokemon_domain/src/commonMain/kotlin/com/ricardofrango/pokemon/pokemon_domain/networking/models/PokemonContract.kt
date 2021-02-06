package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonContract(
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("order")
    val order: Int,
    @SerialName("species")
    val species: PokemonSpecieContract,
    @SerialName("sprites")
    val sprites: PokemonSpriteContract,
    @SerialName("forms")
    val forms: List<PokemonFormContract>,
    @SerialName("types")
    val types: List<PokemonTypeContract>
)
