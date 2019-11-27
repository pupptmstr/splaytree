package com.pupptmstr.splaytree.views

import javafx.scene.Parent
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.ScrollPane
import javafx.scene.paint.Color
import tornadofx.*

class SplayTreeView : View() {

    private lateinit var gc: GraphicsContext

    override val root: Parent = scrollpane {
        hbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
        prefViewportHeight = 1000.0
        prefViewportWidth = 1000.0
        canvas(5000.0, 5000.0) {
            gc = graphicsContext2D
        }
    }

    fun drawShapes(list: List<String>) {
        gc.clearRect(0.0, 0.0, 1000.0, 1000.0)
        gc.fill = Color.ALICEBLUE
        gc.stroke = Color.BLACK
        gc.lineWidth = 1.0

        var indexOfRoot: Int? = null
        var levelNums = 0
        for (i in list.indices) {
            val level = list[i].split("-")[1].toInt()
            if (level > levelNums) {
                levelNums = level
            }
        }
        //centerX = ((2.0.pow(levelNums) + levelNums) * 50).toInt() + 10
        val relationList = mutableListOf<Pair<Int, Int>>()//лист пар значение-уровень
        //элементы расставлены для удобного определения отношений между узлами дерева
        for (i in list.indices) {
            val splitted = list[i].split("-")
            val level = splitted[1].toInt()
            val value = splitted[0].toInt()
            if (level == 0) {
                indexOfRoot = i
            }
            gc.strokeOval(findPlaceX(i), findPlaceY(level), 50.0, 50.0)
            gc.strokeText(value.toString(), findPlaceX(i), findPlaceY(level) - 1.0)
            relationList.add(Pair(value, level))
        }
    }


    private fun findPlaceX(positionInList: Int): Double {
        return ((70 * positionInList) + 15).toDouble()

    }

    private fun findPlaceY(level: Int): Double {
        return (level * 70 + 20).toDouble()

    }


}
