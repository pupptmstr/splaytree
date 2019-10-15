package com.pupptmstr.splaytree.model

import java.lang.Exception
import java.util.*

class SplayTree() : SortedSet<SplayNode> {
    override var size = 0

    private val body = mutableListOf<SplayNode>()

    override fun add(element: SplayNode?): Boolean {
        return try {
            body.add(element!!)
            size++
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun addAll(elements: Collection<SplayNode>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): MutableIterator<SplayNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contains(element: SplayNode?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<SplayNode>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<SplayNode>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun comparator(): Comparator<in SplayNode>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun last(): SplayNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() : String {
        return body.toString()
    }

    fun toList(): List<Int> {
        val res = mutableListOf<Int>()
        body.forEach {
            res.add(it.content)
        }
        return res
    }

}