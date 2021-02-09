package com.ricardofrango.pokemon.pokemon_domain


import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    actual fun getColorFrom(text: String): Int {
        TODO("Not yet implemented")
    }

    actual fun isDark(color: Int): Boolean {
        TODO("Not yet implemented")
    }
}