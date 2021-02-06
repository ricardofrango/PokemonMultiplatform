package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChainContract(
    @SerialName("evolves_to")
    val evolves_to: List<ChainContract>,
    @SerialName("is_baby")
    val is_baby: Boolean,
    @SerialName("species")
    val species: PokemonSpecieContract,
)