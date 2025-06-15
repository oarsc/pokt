package org.oar.idle.ui.boilerplate

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.MouseEvent

class Button(
    src: String,
    alt: String
): HTMLObservableElement<HTMLImageElement>("img", "btn") {
    var onclick: ((MouseEvent) -> Unit)? = null

    init {
        element.src = src
        element.alt = alt
        element.onclick = {
            onclick?.invoke(it)
        }
    }

    companion object {
        init {
            style {
                ".btn" {
                    "marginLeft" to "auto"
                    "cursor" to "pointer"
                    "display" to "block"
                }
            }
        }
    }
}