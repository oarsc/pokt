package org.oar.idle.ui.boilerplate

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.Utils.createElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.HTMLSpanElement

abstract class StatElement(
    initValue: Int = 0,
    totalValue: Int = 0,
    subclass: String,
): HTMLObservableElement<HTMLParagraphElement>("p", "stat $subclass") {

    private val valueElement = createElement<HTMLSpanElement>("span")
    private val totalElement = createElement<HTMLSpanElement>("total")

    var value: Int by renderProperty(initValue, identifier = 1)
    var total: Int by renderProperty(totalValue, identifier = 2)

    init {
        valueElement.apply {
            textContent = initValue.toString()
            className = "value"
        }
        totalElement.apply {
            textContent = totalValue.toString()
            className = "total"
        }
    }

    protected fun appendElements() {
        append {
            +valueElement
            +totalElement
        }
    }

    override fun render(identifier: Int) {
        when (identifier) {
            1 -> valueElement.textContent = "$value"
            2 -> totalElement.textContent = "$total"
        }
    }

    companion object {
        init {
            style {
                ".stat > *" {
                    "verticalAlign" to "middle"
                }
                ".stat .value" {
                    "fontWeight" to "bold"
                }
                ".stat .total" {
                    "fontSize" to "0.8em"
                }
                ".stat .total::before" {
                    "content" to "\" / \""
                }
            }
        }
    }
}