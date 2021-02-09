package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.IPokemonImageModel

class PokemonImageViewHolder<T : IPokemonImageModel>(view: ImageRowView) :
    RecyclerView.ViewHolder(view) {

    fun bind(
        item: T,
        widthMatchParent: Boolean,
        onItemClicked: ((positionClicked: Int, itemClicked: T, viewClicked: View) -> Unit)?
    ) {
        (itemView as ImageRowView).bind(item, widthMatchParent)
        onItemClicked?.let {
            itemView.setOnClickListener {
                onItemClicked.invoke(adapterPosition, item, it)
            }
        } ?: itemView.setOnClickListener(null)
    }
}