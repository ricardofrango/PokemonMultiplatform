package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel

class PokemonsAdapter(
    private val callback: IPokemonsAdapter,
    private val pokemonsList: MutableList<PokemonItemModel> = mutableListOf()
) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(PokemonRowView(parent.context))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonsList[position]) { itemClicked, posClicked, viewClicked ->
            callback.onPokemonClicked(itemClicked, posClicked, viewClicked)
        }
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

    fun setItems(items : List<PokemonItemModel>) {
        pokemonsList.clear()
        pokemonsList.addAll(items)
        notifyItemRangeChanged(0, pokemonsList.size)
    }

    fun addItems(items : List<PokemonItemModel>) {
        val initialPos = pokemonsList.size
        pokemonsList.addAll(items)
        notifyItemRangeInserted(initialPos, items.size)
    }
}