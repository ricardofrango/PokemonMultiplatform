package com.ricardofrango.pokemon.pokemon_domain.networking

import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonContract
import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonListContract
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class PokemonHttpClient {

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        private const val POKEMON_LIST = "${BASE_URL}pokemon?offset=[OFFSET]&limit=[LIMIT]"
        private const val POKEMON = "${BASE_URL}pokemon/"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getPokemons(offset : Int, limit : Int = 40): PokemonListContract {
        return httpClient.get(POKEMON_LIST.replace("[OFFSET]", offset.toString()).replace("[LIMIT]", limit.toString()))
    }

    suspend fun getPokemonByNumber(number : Int): PokemonContract {
        return httpClient.get("$POKEMON$number")
    }

    suspend fun getPokemonByPath(path : String): PokemonContract {
        return httpClient.get(path)
    }
}