package com.ricardofrango.pokemon.pokemon_domain.repository

import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonContract
import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonListContract

interface PokemonRepository {
    suspend fun getPokemonList(offset : Int) : PokemonListContract
    suspend fun getPokemonByNumber(number: Int): PokemonContract
    suspend fun getPokemonByUrl(path: String): PokemonContract
}