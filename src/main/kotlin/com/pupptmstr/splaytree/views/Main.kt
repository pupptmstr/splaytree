package com.pupptmstr.splaytree.views

import javafx.scene.control.Alert
import tornadofx.*
import java.text.ParseException

fun main(args: Array<String>) {
    try {
        launch<MyApp>(args)
    } catch (e: ParseException) {
        alert(Alert.AlertType.ERROR, "Неправильное число в строке, ну ка введи норально")
    }

}

class MyApp : App(MainView::class)