package org.oar.idle.ui.boilerplate

import org.oar.idle.custom.Utils.createElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLImageElement

class ImageStatElement(
    source: String,
    initValue: Int = 0,
    totalValue: Int = 0,
): StatElement(initValue, totalValue, "i") {

    private val imageElement = createElement<HTMLImageElement>("img")

    init {
        imageElement.apply {
            src = source
            className = "image"
        }
        append {
            +imageElement
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