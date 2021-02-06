package com.ricardofrango.pokemon.pokemon_domain

import android.graphics.Color
import androidx.core.graphics.ColorUtils

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual fun getColorFrom(text: String) : Int {
        return try {
            Color.parseColor(text)
        } catch (error : IllegalArgumentException) {
            Color.BLACK
        }
    }

    actual fun isDark(color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) < 0.5
    }
}
