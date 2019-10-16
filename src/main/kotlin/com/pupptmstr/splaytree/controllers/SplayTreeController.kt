package com.pupptmstr.splaytree.controllers

import com.pupptmstr.splaytree.model.SplayNode
import com.pupptmstr.splaytree.model.SplayTree
import com.pupptmstr.splaytree.views.SplayTreeView
import tornadofx.Controller

class SplayTreeController: Controller() {

    private val splayTree = SplayTree()

    fun addDataToTree(inputValue: Int) : String {
        splayTree.add(SplayNode(null, null, null, inputValue))
        println(splayTree.toString())
        find<SplayTreeView>().drawShapes(splayTree.toList())
        return "Добавлено"
    }

    fun removeDataFromTree(inputValue: Int) : String {
        splayTree.remove(SplayNode(null, null, null, inputValue))
        println(splayTree.toString())
        find<SplayTreeView>().drawShapes(splayTree.toList())
        return "Удалено"
    }

    fun searchDataInTree(inputValue: Int) : String {
        return when (splayTree.contains(SplayNode(null, null, null, inputValue))) {
            true -> "Элемент найден"
            false -> "Элемента нет в дереве"
        }
    }

    fun getTree(): SplayTree {
        return splayTree
    }

    fun clearTree() {
        splayTree.clear()
    }

    fun isEmty(): Boolean {
        return splayTree.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return splayTree.isNotEmpty()
    }
}