package org.oar.idle.ui

import kotlinx.browser.localStorage
import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.DIV
import org.oar.idle.ui.pokemon.PokeContainer
import org.oar.idle.utils.Export.pokemonData
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get

class ContentDiv : HTMLBlock<HTMLDivElement>(DIV, id = "content") {

    init {
        val data = read(pokemonData)!!

        readDiscards().forEach {
            data[it].discarded = true
        }

        +Header
        +FixedMenu
        +PokeContainer(data)
        +LeftScroller()
    }

    private fun readDiscards(): Array<Int> = localStorage["discards"]?.let {
        JSON.parse<Array<Int>>(it)
    } ?: emptyArray()
}
