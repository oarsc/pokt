package org.oar.idle.utils

import org.oar.idle.lib.ExportId
import org.oar.idle.lib.NotifierId
import org.oar.idle.model.PokemonData

object Export {
    val inputId = object : ExportId<String>() {}
    val pokemonData = object : ExportId<Array<PokemonData>>() {}
}

object Notifier {
    val discardModification = object : NotifierId<Unit>() {}
}