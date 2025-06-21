package org.oar.idle.custom

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement

object Utils {
    fun setTimeout(delay: Int, runnable: () -> Unit) = window.setTimeout(runnable, delay)
    fun clearTimeout(timeoutId: Int) = window.clearTimeout(timeoutId)

    fun setInterval(delay: Int, runnable: () -> Unit) = window.setInterval(runnable, delay)
    fun clearInterval(intervalId: Int) = window.clearInterval(intervalId)

    fun <T: HTMLElement> createElement(tagName: String) = document.createElement(tagName) as T
}