package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionChainDetailContract(
    @SerialName("id")
    val id: Int,
    @SerialName("chain")
    val chain: ChainContract
)