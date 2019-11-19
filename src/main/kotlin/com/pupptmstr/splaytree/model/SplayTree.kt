package com.pupptmstr.splaytree.model

import java.util.*

class SplayTree() : SortedSet<SplayNode> {
    override var size = 0
    private var root: SplayNode? = null

    fun add(element: Int): Boolean{
        return add(SplayNode(element, null, null, null))
    }
    override fun add(element: SplayNode?): Boolean {
        try {
            var z: SplayNode? = root
            var p: SplayNode? = null
            loop@ while (z != null) {
                p = z
                z = when {
                    (element!!.element > p.element) -> z.right
                    (element.element < p.element) -> z.left
                    else -> break@loop
                }
            }
            z = SplayNode(element!!.element, null, null, p)
            when {
                (p == null) -> root = z
                (element.element > p.element) -> p.right = z
                (element.element < p.element) -> p.left = z
            }
            splay(z)
            size++
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun addAll(elements: Collection<SplayNode>): Boolean {
        elements.forEach {
            val answer = add(it)
            if (!answer) {
                return false
            }
        }
        return true
    }

    override fun clear() {
        root = null
        size = 0
    }

    override fun iterator(): MutableIterator<SplayNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contains(element: SplayNode?): Boolean {
        TODO("not implemented")
    }

    override fun tailSet(fromElement: SplayNode?): SortedSet<SplayNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<SplayNode>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun first(): SplayNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun headSet(toElement: SplayNode?): SortedSet<SplayNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subSet(fromElement: SplayNode?, toElement: SplayNode?): SortedSet<SplayNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(element: SplayNode?): Boolean {
        TODO("not implemented")
    }

    override fun retainAll(elements: Collection<SplayNode>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<SplayNode>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        return root == null
    }

    override fun comparator(): Comparator<in SplayNode>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun last(): SplayNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun rotate(parent: SplayNode, child: SplayNode) {
        TODO("not implemented")
    }

    private fun splay(aim: SplayNode) {
        TODO("not implemented")
    }

    private fun zigZig() {
        TODO("not implemented")
    }

    private fun zigZag() {
        TODO("not implemented")
    }

    private fun zig() {

    }

    override fun toString() : String {
        return "Не готово"
        TODO("намутить перебор тела и пересование его в стрингу")

    }

    fun toList(): List<Int> {
        val res = mutableListOf<Int>()
        return res
        TODO("сделать перебор тела")

    }

    fun getBody(): SplayNode? {
        return root
    }

}