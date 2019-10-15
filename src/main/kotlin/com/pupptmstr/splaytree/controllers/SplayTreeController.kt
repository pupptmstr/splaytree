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
        return "Добавил"
    }

    fun removeDataFromTree(inputValue: Int) : String {
        return "Ну допустим удалил $inputValue из дерева"
    }

    fun searchDataInTree(inputValue: Int) : String {
        return "НУ вот дАпустим нашел $inputValue?!"
    }
}