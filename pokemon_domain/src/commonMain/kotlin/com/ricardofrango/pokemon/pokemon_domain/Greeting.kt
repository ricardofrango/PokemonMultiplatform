package com.ricardofrango.pokemon.pokemon_domain


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
