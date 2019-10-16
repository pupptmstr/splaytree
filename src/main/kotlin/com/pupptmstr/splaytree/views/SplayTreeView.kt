package com.pupptmstr.splaytree.views

import javafx.scene.Parent
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import tornadofx.*

class SplayTreeView() : View() {

    lateinit var gc: GraphicsContext

    override val root: Parent = vbox {
        canvas(1400.0, 1400.0) {
            gc = graphicsContext2D
        }
    }

    fun drawShapes(list: List<Int>) {
        gc.clearRect(0.0,0.0, 1400.0, 1400.0)
        gc.fill = Color.ALICEBLUE
        gc.stroke = Color.BLACK
        gc.lineWidth = 1.0
        val startx = 10
        for (i in 0 until list.size) {
            gc.strokeOval(findPlaceX(i), findPlaceY(i), 50.0, 50.0)
            gc.strokeText(list[i].toString(), findPlaceX(i), findPlaceY(i)-1.0)
        }
    }

    fun findPlaceX(num: Int): Double {
        return ((num % 20) * 65.0) + 15
    }

    fun findPlaceY(num: Int): Double {
        return ((num / 20) * 65.0) + 15
    }


}
