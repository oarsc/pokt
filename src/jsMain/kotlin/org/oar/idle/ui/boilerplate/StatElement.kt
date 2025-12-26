package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.P
import org.oar.idle.lib.HTMLDefinitionConstants.SPAN
import org.oar.idle.lib.style
import org.w3c.dom.HTMLParagraphElement

abstract class StatElement(
    initValue: Int = 0,
    totalValue: Int = 0,
    subclass: String,
): HTMLBlock<HTMLParagraphElement>(P, "stat $subclass") {

    private val valueElement = SPAN("value")
    private val totalElement = SPAN("total")

    var value: Int by renderProperty(initValue, identifier = 1)
    var total: Int by renderProperty(totalValue, identifier = 2)

    init {
        valueElement.apply {
            -"$initValue"
        }
        totalElement.apply {
            -"$totalValue"
        }
    }

    protected fun appendElements() {
        +valueElement
        +totalElement
    }

    override fun render(identifier: Int) {
        when (identifier) {
            1 -> valueElement.apply {
                -"$value"
            }
            2 -> totalElement.apply {
                -"$total"
            }
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