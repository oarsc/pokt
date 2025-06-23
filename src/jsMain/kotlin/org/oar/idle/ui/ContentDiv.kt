package org.oar.idle.ui

import kotlinx.browser.localStorage
import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.ui.pokemon.PokeContainer
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get

class ContentDiv : HTMLObservableElement<HTMLDivElement>("div", id = "content") {

    init {
        val data = read(pokemonData)!!

        readDiscards().forEach {
            data[it].discarded = true
        }

        append {
            +Header
            +FixedMenu
            +PokeContainer(data)
            +LeftScroller()
        }
    }

    private fun readDiscards(): Array<Int> = localStorage["discards"]?.let {
        JSON.parse<Array<Int>>(it)
    } ?: emptyArray()
}
