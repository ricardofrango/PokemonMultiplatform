package com.ricardofrango.pokemon.pokemon_domain.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpriteContract(
    @SerialName("back_default")
    val back_default: String? = null,
    @SerialName("back_female")
    val back_female: String? = null,
    @SerialName("back_shiny")
    val back_shiny: String? = null,
    @SerialName("back_shiny_female")
    val back_shiny_female: String? = null,
    @SerialName("front_default")
    val front_default: String? = null,
    @SerialName("front_female")
    val front_female: String? = null,
    @SerialName("front_shiny")
    val front_shiny: String? = null,
    @SerialName("front_shiny_female")
    val front_shiny_female: String? = null,
    @SerialName("other")
    val other: PokemonOtherSpritesContract
)