package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.DIV
import org.oar.idle.lib.HTMLDefinitionConstants.SPAN
import org.oar.idle.lib.style
import org.oar.idle.utils.Utils.setInterval
import org.w3c.dom.HTMLDivElement

class CounterElement: HTMLBlock<HTMLDivElement>(DIV, id = "counter") {

    private var energy: Int by renderProperty(0, identifier = 1)
    private val textElement = SPAN()

    init {
        setInterval(250) {
            energy++
        }

        +DIV(id = "subContent") {
            +textElement
        }
    }

    override fun render(identifier: Int) {
        when (identifier) {
            1 -> textElement.apply {
                -"$energy"
            }
        }
    }

    companion object {
        init {
            style {
                "#counter" {
                    "fontFamily" to "sans-serif"
                }
            }
        }
    }
}