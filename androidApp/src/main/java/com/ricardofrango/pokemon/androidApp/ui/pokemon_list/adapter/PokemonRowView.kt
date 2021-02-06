package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.Gravity.CENTER_VERTICAL
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel

class PokemonRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val pokemonImage: AppCompatImageView by lazy { findViewById(R.id.ivPokemonImage) }
    private val pokemonName: AppCompatTextView by lazy { findViewById(R.id.tvPokemonName) }
    private val pokemonNumber: AppCompatTextView by lazy { findViewById(R.id.tvPokemonNumber) }

    init {
        inflate(context, R.layout.row_pokemon, this)

        gravity = CENTER_VERTICAL
        orientation = HORIZONTAL
    }

    fun bind(pokemonItemModel: PokemonItemModel) {
        Glide.with(context).load(pokemonItemModel.imageUrl).into(pokemonImage)
        pokemonNumber.text = "${pokemonItemModel.number}"
        pokemonName.text = pokemonItemModel.name
    }

}
