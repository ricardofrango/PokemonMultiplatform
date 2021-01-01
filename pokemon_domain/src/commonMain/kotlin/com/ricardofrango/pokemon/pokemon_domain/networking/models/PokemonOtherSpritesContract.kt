package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonOtherSpritesContract(
    @SerialName("dream_world")
    val dream_world: PokemonDreamWorldSpriteContract,
    @SerialName("official-artwork")
    val official_artwork: PokemonOfficialArtworkSpriteContract,
)
