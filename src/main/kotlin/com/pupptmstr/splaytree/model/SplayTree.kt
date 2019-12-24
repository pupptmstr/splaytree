package com.pupptmstr.splaytree.model

import java.util.*

class SplayTree : SortedSet<SplayNode> {
    override var size = 0
    private var root: SplayNode? = null
    private val listOfHeadSets = ArrayList<SudoSet>()
    private val listOfTailSets = ArrayList<SudoSet>()
    private val listOfSubSets = ArrayList<SudoSet>()


    fun add(element: Int): Boolean{
        return add(SplayNode(element, null, null, null))
    }

    @Throws(java.lang.IllegalArgumentException::class)
    override fun add(element: SplayNode?): Boolean {
        try {
            var current: SplayNode? = root
            var parent: SplayNode? = null
            while (current != null) {
                parent = current
                current = when {
                    (element!!.element > parent.element) -> current.right
                    (element.element < parent.element) -> current.left
                    else -> throw java.lang.IllegalArgumentException()
                }
            }
            current = SplayNode(element!!.element, null, null, parent)
            when {
                (parent == null) -> root = current
                (element.element > parent.element) -> parent.right = current
                (element.element < parent.element) -> parent.left = current
            }
            splay(current)
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

    fun remove(element: Int): Boolean {
        val node = findNode(element)
        return remove(node)
    }

    override fun remove(element: SplayNode?): Boolean {
        try {
            if (element == null) {
                throw IllegalArgumentException()
            }
            if (!contains(element)) {
                throw IllegalArgumentException()
            }

            splay(element)

            if ((element.left != null) && (element.right != null)) {
                var min = element.left
                while (min!!.right != null) {
                    min = min.right
                }

                min.right = element.right
                element.right!!.parent = min
                element.left!!.parent = null
                root = element.left

            } else if (element.right != null) {

                element.right!!.parent = null
                root = element.right

            } else if (element.left != null) {

                element.left!!.parent = null
                root = element.left

            } else {
                root = null
            }

            element.parent = null
            element.left = null
            element.right = null
            size--
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun removeAll(elements: Collection<SplayNode>): Boolean {
        elements.forEach {
            val answer = remove(it)
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

    fun contains(element: Int): Boolean {
        return contains(SplayNode(element, null, null, null))
    }

    override fun contains(element: SplayNode?): Boolean {
        return findNode(element!!.element) != null
    }

    override fun containsAll(elements: Collection<SplayNode>): Boolean {
        elements.forEach {
            val answer = contains(it)
            if (!answer) {
                return false
            }
        }
        return true
    }

    private fun findNode(element: Int): SplayNode? {
        var prevNode: SplayNode? = null
        var z = root

        while (z != null) {
            prevNode = z
            z = when {
                element > z.element -> z.right
                element < z.element -> z.left
                else -> {
                    splay(z)
                    return z
                }
            }

        }

        if (prevNode != null) {
            splay(prevNode)
            return null
        }
        return null
    }

    override fun iterator(): MutableIterator<SplayNode> = Iterator()

    inner class Iterator internal constructor(): MutableIterator<SplayNode> {
        private var nodes: Stack<SplayNode> = Stack()
        private lateinit var current: SplayNode

        init {
            leftMostInorder(root)
        }

        private fun leftMostInorder(mainRoot: SplayNode?) {
            var root = mainRoot
            while (root != null) {
                nodes.push(root)
                root = root.left
            }
        }

        override fun hasNext(): Boolean = nodes.size > 0

        override fun next(): SplayNode {
            current = nodes.pop();
            if (current.right != null) {
                leftMostInorder(current.right);
            }
            return current;
        }

        override fun remove() {
            if (current != null) {
                this@SplayTree.remove(current)
            }
        }
    }

    private fun inorderIterator(): List<String> {
        return inorderIterator(root, 0)
    }

    private fun inorderIterator(r: SplayNode?, counter: Int): List<String> {
        val res = mutableListOf<String>()
        if (r != null) {
            res.addAll(inorderIterator(r.left, counter+1))
            res.add("${r.element}-$counter")
            res.addAll(inorderIterator(r.right, counter + 1))
        }
        return res
    }

    override fun first(): SplayNode {
        var current: SplayNode = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    override fun last(): SplayNode {
        var current: SplayNode = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current
    }

    inner class SudoSet(val fromElement: Int?, val toElement: Int?) : TreeSet<Int>() {
        override fun add(element: Int): Boolean {
            val value = element as Int? ?: return false
            if (toElement == null) {
                require(value >= fromElement!!)
                if (!this@SplayTree.contains(value)) {
                    this@SplayTree.add(value)
                }
                super.add(value)
                return true
            }

            if (fromElement == null) {
                require(value < toElement)
                if (!this@SplayTree.contains(value)) {
                    this@SplayTree.add(value)
                }
                super.add(value)
                return true
            }

            if (value < toElement && value >= fromElement) {
                if (!this@SplayTree.contains(value)) {
                    this@SplayTree.add(value)
                }
                super.add(value)
            } else {
                throw IllegalArgumentException()
            }
            return true
        }

        override fun remove(element: Int): Boolean {
            val value = element as Int? ?: return false
            this@SplayTree.remove(value)
            return super.remove(value)
        }
    }

    private fun updateSets() {
        for (k in listOfSubSets) {
            for (value in this) {
                if (value < k.toElement!! && value >= k.fromElement!!) {
                    k.add(value.element)
                }
            }
        }
        for (k in listOfHeadSets) {
            for (value in this) {
                if (value < k.toElement!!) {
                    k.add(value.element)
                } else {
                    break
                }
            }
        }
        for (k in listOfTailSets) {
            for (value in this) {
                if (value >= k.fromElement!!) {
                    k.add(value.element)
                }
            }
        }
    }
    override fun tailSet(fromElement: SplayNode?): SortedSet<SplayNode> {
        val tailSet = SudoSet(fromElement!!.element, null)
        listOfTailSets.add(tailSet)
        updateSets()
        return tailSet as SortedSet<SplayNode>
    }

    override fun headSet(toElement: SplayNode?): SortedSet<SplayNode> {
        val headSet = SudoSet(null, toElement!!.element)
        listOfHeadSets.add(headSet)
        updateSets()
        return headSet as SortedSet<SplayNode>
    }

    override fun subSet(fromElement: SplayNode?, toElement: SplayNode?): SortedSet<SplayNode> {
        require(fromElement!! < toElement!!.element)
        val subSet = SudoSet(fromElement.element, toElement.element)
        listOfSubSets.add(subSet)
        updateSets()
        return subSet as SortedSet<SplayNode>
    }

    override fun retainAll(elements: Collection<SplayNode>): Boolean {
        try {
            val existingElements = mutableListOf<Int>()
            elements.forEach {
                if (contains(it.element)) {
                    existingElements.add(it.element)
                }
            }
            clear()
            existingElements.forEach {
                add(it)
            }
            return true
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun isEmpty(): Boolean {
        return root == null
    }

    override fun comparator(): Comparator<in SplayNode>? = null

    private fun splay(aim: SplayNode) {
        while (aim.parent != null) {
            val parent =  aim.parent
            val grandParent = parent!!.parent

            if (grandParent == null) {
                if (aim == parent.left) {
                    makeLeftChildParent(aim, parent)
                } else {
                    makeRightChildParent(aim, parent)
                }
            } else {
                if (aim == parent.left) {
                    if (parent == grandParent.left) {
                        makeLeftChildParent(parent, grandParent)
                        makeLeftChildParent(aim, parent)
                    } else {
                        makeLeftChildParent(aim, aim.parent)
                        makeRightChildParent(aim, aim.parent)
                    }
                } else {
                    if (parent == grandParent.left) {
                        makeRightChildParent(aim, aim.parent)
                        makeLeftChildParent(aim, aim.parent)
                    } else {
                        makeRightChildParent(parent, grandParent)
                        makeRightChildParent(aim, parent)
                    }
                }
            }
        }
        root = aim
    }

    private fun makeLeftChildParent(child: SplayNode?, parent: SplayNode?) {
        if ((child == null) || (parent == null) || (parent.left != child) || (child.parent != parent)) {
            throw RuntimeException("WRONG")
        }

        if (parent.parent != null) {
            if (parent == parent.parent!!.left) {
                parent.parent!!.left = child
            } else {
                parent.parent!!.right = child
            }
        }

        if (child.right != null) {
            child.right!!.parent = parent
        }

        child.parent = parent.parent
        parent.parent = child
        parent.left = child.right
        child.right = parent
    }

    private fun makeRightChildParent(child: SplayNode?, parent: SplayNode?) {
        if ((child == null) || (parent == null) || (parent.right != child) || (child.parent != parent)) {
            throw RuntimeException("WRONG")
        }

        if (parent.parent != null) {
            if (parent.parent!!.left == parent) {
                parent.parent!!.left = child
            } else {
                parent.parent!!.right = child
            }
        }

        if (child.left != null) {
            child.left!!.parent = parent
        }

        child.parent = parent.parent
        parent.parent = child
        parent.right = child.left
        child.left = parent
    }

    override fun toString() : String = inorderIterator().joinToString(separator = "//")

    fun toList(): List<String> = inorderIterator()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SplayTree

        if (size != other.size) return false
        if (root != other.root) return false

        if (!containsAll(listOf(other.root))) return false

        return true
    }

    override fun hashCode(): Int {
        var result = size
        result = 31 * result + (root?.hashCode() ?: 0)
        return result
    }


}