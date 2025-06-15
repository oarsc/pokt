package org.oar.idle.ui.boilerplate

import kotlinx.browser.document
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.HTMLSpanElement

class StatElement(
    title: String,
    initValue: Int = 0
): HTMLObservableElement<HTMLParagraphElement>("p", "stat") {

    private val titleElement = document.createElement("span") as HTMLSpanElement
    private val valueElement = document.createElement("span") as HTMLSpanElement

    var value: Int by renderProperty(0, identifier = 1)

    init {
        titleElement.apply {
            textContent = title
            className = "title"
        }
        valueElement.apply {
            textContent = initValue.toString()
            className = "value"
        }

        append {
            +titleElement
            +valueElement
        }
    }

    override fun render(identifier: Int) {
        when (identifier) {
            1 -> valueElement.textContent = "$value"
        }
    }

    companion object {
        init {
            style {
                "span.title::after" {
                    "content" to "\": \""
                }
                ".stat .value" {
                    "fontWeight" to "bold"
                }
            }
        }
    }
}