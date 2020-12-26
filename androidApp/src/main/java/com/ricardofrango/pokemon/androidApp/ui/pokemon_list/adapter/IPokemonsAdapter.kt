package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.view.View
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel

interface IPokemonsAdapter {
    fun onPokemonClicked(itemClicked: PokemonItemModel, posClicked: Int, viewClicked: View)
}
