package org.oar.idle.constants

import org.oar.idle.model.PokemonData

object ExportId {
    val inputId = object : ExportId<String>() {}
    val pokemonData = object : ExportId<Array<PokemonData>>() {}

    open class ExportId<T: Any> internal constructor()
}