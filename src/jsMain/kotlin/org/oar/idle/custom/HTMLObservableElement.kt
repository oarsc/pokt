package org.oar.idle.custom

import io.nacular.doodle.utils.observable
import kotlinx.browser.document
import org.oar.idle.constants.ExportId.ExportId
import org.w3c.dom.DOMTokenList
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.Node
import kotlin.js.Promise
import kotlin.properties.ReadWriteProperty

open class HTMLObservableElement<E : HTMLElement>(
    tagName: String,
    className: String? = null,
    id: String? = null
) {
    @Suppress("UNCHECKED_CAST")
    val element = document.createElement(tagName) as E

    private val _children = mutableListOf<HTMLObservableElement<*>>()
    val children: List<HTMLObservableElement<*>> = _children
    var parent: HTMLObservableElement<*>? = null
        private set

    init {
        element.apply {
            className?.let { this.className = it }
            id?.let { this.id = it }
        }
        Promise.resolve(Unit).then { render() }
    }

    protected fun <T> renderProperty(
        initial: T,
        onChange: HTMLObservableElement<E>.(old: T, new: T) -> Unit = { _, _ -> }
    ): ReadWriteProperty<HTMLObservableElement<E>, T> = observable(initial) { old, new ->
        onChange(old, new)
        update()
    }

    fun append(build: ElementBuilder.() -> Unit) {
        ElementBuilder(element::appendChild).build()
    }

    inner class ElementBuilder internal constructor(
        val appendChild: (Node) -> Unit,
        val builderElement: Element? = null
    ) {
        val element: Element get() = builderElement!!
        var id: String
            get() = element.id
            set(id) { element.id = id }
        var className: String
            get() = element.className
            set(className) { element.className = className }
        val classList: DOMTokenList get() = element.classList

        operator fun String.invoke(build: ElementBuilder.() -> Unit) {
            val subElement = document.createElement(this)
            appendChild(subElement)

            ElementBuilder(subElement::appendChild, subElement).build()
        }

        @Suppress("LABEL_RESOLVE_WILL_CHANGE")
        operator fun HTMLObservableElement<*>.unaryPlus() {
            this@HTMLObservableElement._children.add(this)
            appendChild(this@unaryPlus.element)
            this@unaryPlus.parent = this@HTMLObservableElement
        }

        operator fun Element.unaryPlus() {
            this@ElementBuilder.appendChild(this)
        }

        operator fun String.unaryPlus() {
            appendChild(document.createTextNode(this))
        }

        operator fun String.unaryMinus() {
            builderElement!!.innerHTML = this
        }
    }

    fun update() = render()
    protected open fun render() {}

    companion object {
        private val mutableMap = mutableMapOf<ExportId<*>, () -> Any>()
        fun <T: Any> expose(id: ExportId<T>, function: () -> T) {
            mutableMap[id] = function
        }

        @Suppress("UNCHECKED_CAST")
        fun <T: Any> read(id: ExportId<T>): T? {
            return mutableMap[id]?.let { it() as T }
        }
    }
}