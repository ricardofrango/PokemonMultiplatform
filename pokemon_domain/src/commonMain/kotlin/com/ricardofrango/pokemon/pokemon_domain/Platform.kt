package com.ricardofrango.pokemon.pokemon_domain

expect class Platform() {
    val platform: String
    actual fun getColorFrom(text: String): Int
    actual fun isDark(color: Int): Boolean
}