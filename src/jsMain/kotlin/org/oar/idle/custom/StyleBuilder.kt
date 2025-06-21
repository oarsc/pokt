package org.oar.idle.custom

import kotlinx.browser.document
import org.oar.idle.custom.Utils.createElement
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet

private val styleElement = createElement<HTMLStyleElement>("style").apply {
    type = "text/css"
    document.head?.appendChild(this)
}

fun style(builder: CSSBuilder.() -> Unit) {
    val sheet = styleElement.sheet as CSSStyleSheet
    CSSBuilder(sheet).builder()
}

class CSSBuilder(private val sheet: CSSStyleSheet) {
    operator fun String.invoke(buildProperties: CSSPropertiesBuilder.() -> Unit) {
        val cssBuilder = CSSPropertiesBuilder()
            .apply { buildProperties() }

        cssBuilder.build()
            ?.also {
                sheet.insertRule("$this{$it}", sheet.cssRules.length)
            }
    }
}

class CSSPropertiesBuilder {
    private val rules = mutableListOf<String>()

    infix fun String.to(other: Any) {
        val kebabCaseName = replace(Regex("([A-Z])")) { "-${it.value.lowercase()}" }
        rules.add("$kebabCaseName:$other;")
    }

    fun build(): String? = rules
        .takeIf { it.isNotEmpty() }
        ?.joinToString("")
}
