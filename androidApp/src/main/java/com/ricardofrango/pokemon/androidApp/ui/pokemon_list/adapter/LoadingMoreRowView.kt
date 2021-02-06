package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.LinearLayoutCompat
import com.ricardofrango.pokemon.androidApp.R

class LoadingMoreRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.row_loading_more, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        gravity = CENTER
    }
}