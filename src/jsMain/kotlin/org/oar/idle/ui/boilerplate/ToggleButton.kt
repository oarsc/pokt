package org.oar.idle.ui.boilerplate

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLImageElement

class ToggleButton(
    src: String,
    alt: String
): HTMLObservableElement<HTMLImageElement>("img", "toggle-btn") {
    var value = false
        set(value) {
            field = value
            element.classList.toggle("on", value)
        }
    var onchange: ((Boolean) -> Unit)? = null

    init {
        element.src = src
        element.alt = alt
        element.onclick = {
            value = !value
            onchange?.invoke(value)
        }
    }

    companion object {
        init {
            style {
                ".toggle-btn" {
                    "marginLeft" to "auto"
                    "cursor" to "pointer"
                    "opacity" to "0.3"
                    "display" to "block"
                }
                ".toggle-btn.on" {
                    "opacity" to "1"
                }
            }
        }
    }
}