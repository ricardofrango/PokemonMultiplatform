package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.IPokemonImageModel

class PokemonImagesAdapter<T>(
    private val widthMatchParent: Boolean,
    private val callback: IPokemonImagesAdapter<T>? = null,
    private val images: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<PokemonImageViewHolder<T>>() where T : IPokemonImageModel {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonImageViewHolder<T> {
        return PokemonImageViewHolder(ImageRowView(parent.context))
    }

    override fun onBindViewHolder(holder: PokemonImageViewHolder<T>, position: Int) {
        callback?.let {
            holder.bind(
                images[position],
                widthMatchParent
            ) { positionClicked, itemClicked, viewClicked ->
                callback.onItemClicked(positionClicked, itemClicked, viewClicked)
            }
        } ?: holder.bind(images[position], widthMatchParent, null)

    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setImages(images: List<T>) {
        this.images.run {
            clear()
            addAll(images)
        }
        notifyDataSetChanged()
    }
}