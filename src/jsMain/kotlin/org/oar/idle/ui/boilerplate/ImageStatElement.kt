package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLDefinitionConstants.IMG
import org.oar.idle.lib.style

class ImageStatElement(
    source: String,
    initValue: Int = 0,
    totalValue: Int = 0,
): StatElement(initValue, totalValue, "i") {

    private val imageElement = IMG("image")

    init {
        +IMG("image"){
            element.src = source
        }

        appendElements()
    }

    companion object {
        init {
            style {
                ".stat.i" {
                    "margin" to "1px"
                }
                ".stat.i .image" {
                    "width" to "25px"
                    "borderRadius" to "6px"
                    "marginRight" to "5px"
                }
            }
        }
    }
}