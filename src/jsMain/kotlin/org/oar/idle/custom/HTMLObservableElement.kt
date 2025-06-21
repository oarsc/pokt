package org.oar.idle.custom

import io.nacular.doodle.utils.observable
import kotlinx.browser.document
import org.oar.idle.constants.ExportId.ExportId
import org.oar.idle.constants.NotifierId.NotifierId
import org.oar.idle.custom.Utils.createElement
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
    val element = createElement<E>(tagName)

    private val _children = mutableListOf<HTMLObservableElement<*>>()
    val children: List<HTMLObservableElement<*>> = _children
    var parent: HTMLObservableElement<*>? = null
        private set

    init {
        element.apply {
            className?.let { this.className = it }
            id?.let { this.id = it }
        }
        Promise.resolve(Unit).then { render(-1) }
    }

    protected fun <T> renderProperty(
        initial: T,
        identifier: Int = -1,
        onChange: HTMLObservableElement<E>.(old: T, new: T) -> Unit = { _, _ -> }
    ): ReadWriteProperty<HTMLObservableElement<E>, T> = observable(initial) { old, new ->
        onChange(old, new)
        update(identifier)
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
            val subElement = createElement<HTMLElement>(this)
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

    private fun update(identifier: Int) = render(identifier)
    protected open fun render(identifier: Int) {}

    companion object {
        private val exportsMap = mutableMapOf<ExportId<*>, () -> Any>()

        fun <T: Any> expose(id: ExportId<T>, function: () -> T) {
            exportsMap[id] = function
        }

        @Suppress("UNCHECKED_CAST")
        fun <T: Any> read(id: ExportId<T>): T? {
            return exportsMap[id]?.let { it() as T }
        }

        private val notifierMap = mutableMapOf<NotifierId, List<() -> Unit>>()

        fun listen(id: NotifierId, function: () -> Unit) {
            val list = notifierMap[id] ?: emptyList()
            notifierMap[id] = list + function
        }

        fun notify(id: NotifierId) {
            notifierMap[id]?.forEach { it() }
        }
    }
}