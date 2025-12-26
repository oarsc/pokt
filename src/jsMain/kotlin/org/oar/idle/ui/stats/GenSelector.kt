package org.oar.idle.ui.stats

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.DIV
import org.oar.idle.lib.HTMLDefinitionConstants.IMG
import org.oar.idle.lib.HTMLDefinitionConstants.SPAN
import org.oar.idle.lib.style
import org.oar.idle.utils.Export.pokemonData
import org.w3c.dom.HTMLDivElement

class GenSelector(
    private val onGenChange: (Int) -> Unit
) : HTMLBlock<HTMLDivElement>(DIV, id = "gen-selector") {

    private var currentGen by renderProperty(0, 0)
    private val maxGen = read(pokemonData)!!.maxOf { it.gen }

    private val span = SPAN()

    init {
        val left = IMG("left-btn") {
            element.apply {
                src = "./icon/left.svg"
                onclick = {
                    if (currentGen > 0) {
                        currentGen--
                    }
                    false
                }
            }
        }
        val right = IMG("right-btn") {
            element.apply {
                src = "./icon/right.svg"
                onclick = {
                    if (currentGen < maxGen) {
                        currentGen++
                    }
                    false
                }
            }
        }

        span.apply {
            -"All gens"
        }

        +left
        +span
        +right
    }

    override fun render(identifier: Int) {
        when(identifier) {
            0 -> {
                span.apply {
                    -if (currentGen == 0) "All gens" else "Gen $currentGen"
                }
                onGenChange(currentGen)
            }
        }
    }

    companion object {
        init {
            style {
                "#gen-selector" {
                }
                "#gen-selector > *" {
                    "verticalAlign" to "middle"
                }
                "#gen-selector > img" {
                    "height" to "24px"
                    "cursor" to "pointer"
                }
                "#gen-selector > span" {
                    "textAlign" to "center"
                    "width" to "100px"
                    "display" to "inline-block"
                }
            }
        }
    }
}
