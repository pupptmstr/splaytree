package com.pupptmstr.splaytree.controllers

import com.pupptmstr.splaytree.model.SplayTree
import com.pupptmstr.splaytree.views.SplayTreeView
import tornadofx.Controller
import java.lang.Exception
import java.util.*

class SplayTreeController : Controller() {

    private val splayTree = SplayTree()

    fun addDataToTree(inputValue: Int): String {
        val ans = splayTree.add(inputValue)
        return if (ans) {
            println(splayTree.toString())
            find<SplayTreeView>().drawShapes(splayTree.toList())
            "Добавлено"
        } else {
            "Такой элемент уже существует"
        }
    }

    fun removeDataFromTree(inputValue: Int): String {
        val ans = splayTree.remove(inputValue)
        return if (ans) {
            println(splayTree.toString())
            find<SplayTreeView>().drawShapes(splayTree.toList())
            "Удалено"
        } else {
            "Удалять нечего. Такого элемента не существует."
        }
    }

    fun searchDataInTree(inputValue: Int): String {
        val ans = splayTree.contains(inputValue)
        println(splayTree.toString())
        find<SplayTreeView>().drawShapes(splayTree.toList())
        return when (ans) {
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

    fun isNotEmpty(): Boolean {
        return splayTree.isNotEmpty()
    }
}