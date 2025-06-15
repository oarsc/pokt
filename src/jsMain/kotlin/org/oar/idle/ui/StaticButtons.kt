package org.oar.idle.ui

import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.ui.boilerplate.Button
import org.oar.idle.ui.boilerplate.ToggleButton
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get
import org.w3c.dom.set

object StaticButtons : HTMLObservableElement<HTMLDivElement>("div", id = "buttons") {

    private val restoreBtn = ToggleButton("./icon/view.svg", "View hidden")
    private val zoomBtn = ToggleButton("./icon/zoom.svg", "Zoom images")
    private val resetBtn = Button("./icon/restart.svg", "Reset selection")
    private val importBtn = Button("./icon/import.svg", "Import")
    private val exportBtn = Button("./icon/export.svg", "Export")

    init {

        val classList = document.body!!.classList

        restoreBtn.onchange = {
            classList.toggle("restore", it)
            localStorage["restore"] = it.toString()
        }

        if (localStorage["restore"] == "true") {
            classList.add("restore")
            restoreBtn.value = true
        }

        zoomBtn.onchange = {
            classList.toggle("zoom", it)
            localStorage["zoom"] = it.toString()
        }

        if (localStorage["zoom"] == "true") {
            classList.add("zoom")
            zoomBtn.value = true
        }

        resetBtn.onclick = {
            val response = window.confirm("Do you really want to reset the current selection?")
            if (response) {
                localStorage.removeItem("discards")
                window.location.reload()
            }
        }

        importBtn.onclick = {
          window.prompt("Add your code:")?.let { code ->
              val discards = decode(code).reversed()
                  .mapIndexedNotNull { idx, it -> if (it == '1') idx else null }
                  .let { JSON.stringify(it) }
              localStorage["discards"] = discards
              window.location.reload()
          }
        }

        exportBtn.onclick = {
            val binary = read(pokemonData)!!.reversed().joinToString("") { if (it.discarded) "1" else "0" }.trimStart('0')
            val code = encode(binary)
            window.prompt("Save this code:", code)
        }

        append {
            +restoreBtn
            +zoomBtn
            +resetBtn
            +importBtn
            +exportBtn
        }

        style {
//                "#buttons" {
//                    "position" to "fixed"
//                    "top" to "20px"
//                    "bottom" to "0"
//                    "right" to "20px"
//                }
        }
    }


    private fun encode(binaryString: String): String =
        jsBigInt("0b$binaryString").toString(BASE)
            .let(window::btoa)

    private fun decode(base36String: String): String {
        val digits = "0123456789abcdefghijklmnopqrstuvwxyz"
        val base = jsBigInt(BASE.toString())

        return base36String
            .let(window::atob)
            .map { digits.indexOf(it) }
            .onEach { require(it >= 0) { "Invalid character in base36 string" } }
            .fold(jsBigInt("0")) { acc, it -> multiplyAdd(acc, base, it) }
            .toString(2)
    }

    private const val BASE = 36
}

external interface JsBigInt {
    fun toString(radix: Int = definedExternally): String
}

@JsName("BigInt")
external fun jsBigInt(value: String): JsBigInt

@Suppress("UnsafeCastFromDynamic")
fun multiplyAdd(bigInt1: JsBigInt, bigInt2: JsBigInt, addValue: Int): JsBigInt {
    return js("bigInt1 * bigInt2 + BigInt(addValue)")
}
