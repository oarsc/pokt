package org.oar.idle.ui

import kotlinx.browser.document
import kotlinx.browser.localStorage
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get
import org.w3c.dom.set

class StaticButtons : HTMLObservableElement<HTMLDivElement>("div", id = "buttons") {

    init {
        val btn1 = document.createElement("button") as HTMLButtonElement
        val btn2 = document.createElement("button") as HTMLButtonElement

        val classList = document.body!!.classList

        btn1.textContent = "restore"
        btn1.onclick = {
            classList.toggle("restore")
            localStorage["restore"] = classList.contains("restore").toString()
            false
        }

        if (localStorage["restore"] == "true") {
            classList.add("restore")
        }

        btn2.textContent = "zoom"
        btn2.onclick = {
            classList.toggle("zoom")
            localStorage["zoom"] = classList.contains("zoom").toString()
            false
        }

        if (localStorage["zoom"] == "true") {
            classList.add("zoom")
        }

        append {
            +btn1
            +btn2
        }
    }

    companion object {
        init {
            style {
                "#buttons" {
                    "position" to "fixed"
                    "top" to "0"
                    "bottom" to "0"
                    "right" to "0"
                }
            }
        }
    }
}
