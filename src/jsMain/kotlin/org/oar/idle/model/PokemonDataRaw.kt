package org.oar.idle.model

import org.oar.idle.custom.Constants.POKEMON_BREAKS

external interface PokemonDataRaw {
    val number: Int
    val key: String
    val name: String
    val types: List<Type>
}

data class PokemonData(
    val number: Int,
    val key: String,
    val name: String,
    val types: List<Type>
) {
    var discarded: Boolean = false
    val gen = POKEMON_BREAKS
        .indexOfFirst { number < it }
        .let { if (it == -1) POKEMON_BREAKS.size else it } + 1

    companion object {
        fun PokemonDataRaw.parse() = PokemonData(
            number = number,
            key = key,
            name = name,
            types = types
        )

        fun Array<PokemonDataRaw>.parse() = map { it.parse() }.toTypedArray()
    }
}
