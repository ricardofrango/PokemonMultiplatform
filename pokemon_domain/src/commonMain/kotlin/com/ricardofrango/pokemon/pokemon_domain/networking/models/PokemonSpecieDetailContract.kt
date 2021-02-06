package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpecieDetailContract(
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("order")
    val order: Int,
    @SerialName("color")
    val color: PokemonColorContract,
    @SerialName("evolution_chain")
    val evolution_chain: PokemonEvolutionChainContract,
    @SerialName("generation")
    val generation: PokemonGenerationContract,
    @SerialName("varieties")
    val varieties: List<PokemonVarietyContract>
)