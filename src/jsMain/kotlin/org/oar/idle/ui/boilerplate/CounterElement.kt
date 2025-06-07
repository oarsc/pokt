package org.oar.idle.ui.boilerplate

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.Utils.createElement
import org.oar.idle.custom.Utils.setInterval
import org.oar.idle.custom.style
import org.w3c.dom.HTMLElement

class CounterElement(
): HTMLObservableElement<HTMLElement>("div", id = "counter") {

    private var energy: Int by renderProperty(0, identifier = 1)
    private val textElement = createElement("span")

    init {
        append {
            "div" {
                id = "subContent"
                +textElement
            }
        }

        setInterval(250) {
            energy++
        }
    }

    override fun render(identifier: Int) {
        println("render, $energy - $identifier")
        when (identifier) {
            1 -> textElement.textContent = "$energy"
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