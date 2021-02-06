package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.view.View
import com.ricardofrango.pokemon.androidApp.ui.pokemon_list.ListMode
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel

class PokemonViewHolder(view: PokemonRowView) : PokemonsAdapterViewHolder(view) {
    fun bind(
        pokemonItemModel: PokemonItemModel,
        listMode: ListMode,
        itemClickedCallback: (itemClicked: PokemonItemModel, posClicked: Int, viewClicked: View) -> Unit
    ) {
        (itemView as PokemonRowView).bind(pokemonItemModel, listMode)
        itemView.setOnClickListener { itemClickedCallback.invoke(pokemonItemModel, adapterPosition, it) }
    }

}
