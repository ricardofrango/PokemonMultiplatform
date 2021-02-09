package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.adapter

import android.view.View

interface IPokemonImagesAdapter<T> {
    fun onItemClicked(positionClicked: Int, itemClicked: T, viewClicked: View)

}
