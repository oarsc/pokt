package org.oar.idle

import kotlinx.browser.document
import kotlinx.browser.window
import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.custom.HTMLObservableElement.Companion.expose
import org.oar.idle.custom.style
import org.oar.idle.model.PokemonData.Companion.parse
import org.oar.idle.model.PokemonDataRaw
import org.oar.idle.ui.ContentDiv
import kotlin.js.Promise

private val body = document.body!!

fun main() {
    style {
        "body" {
            "margin" to "0"
            "fontFamily" to "sans"
        }
    }

    window.fetch("./pokes.json")
        .then { it.json() as Promise<Array<PokemonDataRaw>> }
        .then {
            val data = it.parse()
            expose(pokemonData) { data }
            body.appendChild(ContentDiv().element)
        }
}
