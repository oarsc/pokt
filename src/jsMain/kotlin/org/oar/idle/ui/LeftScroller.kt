package org.oar.idle.ui

import kotlinx.browser.document
import kotlinx.browser.window
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions

class LeftScroller : HTMLObservableElement<HTMLDivElement>("div", id = "scroller") {

    init {
        element.apply {
            val classLists = listOf(classList, document.body!!.classList)
            onmouseover =  {
                classLists.forEach { it.add("expanded") }
            }
            onmouseleave =  {
                classLists.forEach { it.remove("expanded") }
            }
            onmousedown = { ev ->
                val btn = ev.buttons.toInt()

                if (btn == 4) {} // middle click
                else if (btn == 2) { // right click
                    scrollScreen(-384.0)
                } else {
                    scrollScreen(384.0)
                }

                ev.stopPropagation()
                ev.preventDefault()
                false
            }
            oncontextmenu = {
                it.stopPropagation()
                it.preventDefault()
                false
            }
        }
    }

    private fun scrollScreen(value: Double) {
        window.scrollTo(
            ScrollToOptions(
                top = window.scrollY + value,
                behavior= ScrollBehavior.SMOOTH
            )
        )
    }

    companion object {
        private const val SPEED = 0.5
        private const val MAX_SIZE = 40
        init {
            style {
                "#scroller" {
                    "position" to "fixed"
                    "top" to "0"
                    "bottom" to "0"
                    "left" to "0"
                    "width" to "1px"
                    "backgroundColor" to "#244464"
                    "opacity" to 0
                    "transition" to "width ${SPEED}s, opacity ${SPEED/2}s"
                    "boxShadow" to "inset -6px 0 5px -5px black"
                    "cursor" to "pointer"
                }
                "#scroller.expanded" {
                    "width" to "${MAX_SIZE}px"
                    "opacity" to 1
                }

                "body" {
                    "transition" to "margin-left ${SPEED}s"
                }
                "body.expanded" {
                    "margin-left" to "${MAX_SIZE}px"
                }
            }
        }
    }
}
