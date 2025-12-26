package org.oar.idle

import kotlinx.browser.document
import org.oar.idle.lib.HTMLBlock.Companion.HTMLBodyBlock
import org.oar.idle.lib.HTMLBlock.Companion.expose
import org.oar.idle.lib.style
import org.oar.idle.model.PokemonData.Companion.parse
import org.oar.idle.model.PokemonDataRaw
import org.oar.idle.ui.ContentDiv
import org.oar.idle.utils.Export.pokemonData
import org.oar.idle.utils.Utils.fetchJson

private val body = document.body!!

fun main() {
    style {
        "body" {
            "margin" to "0"
            "fontFamily" to "sans"
        }
    }

    HTMLBodyBlock.apply {
        fetchJson<Array<PokemonDataRaw>>("./pokes.json") {
            val data = it.parse()
            expose(pokemonData) { data }

            +ContentDiv()
        }
    }
}
