package org.oar.idle.model

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
    private val discardListeners = mutableListOf<(Boolean) -> Unit>()
    var discarded: Boolean = false
        set(value) {
            field = value
            discardListeners.forEach { it(value) }
        }

    fun setListener(listener: (Boolean) -> Unit) {
        discardListeners.add(listener)
    }

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

enum class Type {
    NORMAL, FIRE, WATER, GRASS, ELECTRIC, ICE, BUG, ROCK, GROUND, FLYING, POISON, FIGHTING, PSYCHIC, GHOST, DRAGON, DARK, STEEL, FAIRY
}