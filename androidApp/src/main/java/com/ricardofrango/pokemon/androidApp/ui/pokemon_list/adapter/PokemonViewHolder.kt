package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel

class PokemonViewHolder(view: PokemonRowView) : RecyclerView.ViewHolder(view) {
    fun bind(
        pokemonItemModel: PokemonItemModel,
        itemClickedCallback: (itemClicked: PokemonItemModel, posClicked: Int, viewClicked: View) -> Unit
    ) {
        (itemView as PokemonRowView).bind(pokemonItemModel)
        itemView.setOnClickListener { itemClickedCallback.invoke(pokemonItemModel, adapterPosition, it) }
    }

}
